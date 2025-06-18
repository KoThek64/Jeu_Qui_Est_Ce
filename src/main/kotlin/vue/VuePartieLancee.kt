package vue

import components.Grille
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextArea
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import modele.Modele

class VuePartieLancee(
    private val modele: Modele
) : BorderPane() {

    // PARTIE DU HAUT
    private val question : TextArea = TextArea("").apply {
        promptText = "Posez votre question ici..."
        prefWidth = 325.0
        prefHeight = 100.0
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
    }

    private val reponseQuestion : TextArea = TextArea("").apply {
        promptText = "La réponse à votre question apparaîtra ici..."
        prefHeight = 100.0
        prefWidth = 250.0
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        isEditable = false
    }

    private val boutonEnvoi : Button = Button("Envoyer").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        prefWidth = 100.0
        style = """
            -fx-background-color: #4169E1;
            -fx-text-fill: white;
            -fx-background-radius: 5;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 0);
        """.trimIndent()

        val defaultStyle = style
        onMouseEntered = EventHandler { style = "$defaultStyle -fx-background-color: #1E90FF;"}
        onMouseExited = EventHandler { style = defaultStyle }
    }

    private val questionAdv : TextArea = TextArea("").apply {
        promptText = "La question de l'adversaire apparaîtra ici..."
        prefWidth = 325.0
        prefHeight = 100.0
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        isEditable = false
    }

    private val boutonOui : Button = Button("OUI").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        prefWidth = 80.0
        style = """
            -fx-background-color: #32CD32;
            -fx-text-fill: white;
            -fx-background-radius: 5;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 0);
        """.trimIndent()

        val defaultStyle = style
        onMouseEntered = EventHandler { style = "$defaultStyle -fx-background-color: #90EE90;" }
        onMouseExited = EventHandler { style = defaultStyle }
    }

    private val boutonNon : Button = Button("NON").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        prefWidth = 80.0
        style = """
            -fx-background-color: #DC143C;
            -fx-text-fill: white;
            -fx-background-radius: 5;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 3, 0, 0, 0);
        """.trimIndent()

        val defaultStyle = style
        onMouseEntered = EventHandler { style = "$defaultStyle -fx-background-color: #FF4444;"}
        onMouseExited = EventHandler { style = defaultStyle }
    }

    // PARTIE DE DROITE
    private val persoChoisi : Button = Button().apply {
        prefWidth = 120.0
        prefHeight = 150.0
        isDisable = true
        style = """
            -fx-opacity: 1.0;
            -fx-background-color: #FFFFFF;
            -fx-border-color: #CCCCCC;
            -fx-border-width: 1px;
        """.trimIndent()
    }

    private var labelNomPersoChoisi : Label = Label("Votre personnage").apply {
        font = Font.font("Arial", FontWeight.BOLD, 14.0)
    }

    private val persoSuspect : Button = Button().apply {
        prefWidth = 120.0
        prefHeight = 150.0
        isDisable = true
        style = """
            -fx-opacity: 1.0;
            -fx-background-color: #FFFFFF;
            -fx-border-color: #CCCCCC;
            -fx-border-width: 1px;
        """.trimIndent()
    }

    private var labelNomPersoSuspect : Label = Label("Personnage suspecté").apply {
        font = Font.font("Arial", FontWeight.BOLD, 14.0)
    }

    private val boutonValiderSuspect : Button = Button("Valider").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        prefWidth = 120.0
        style = "-fx-background-color: #90EE90;"
    }

    private val body : GridPane = GridPane()

    private val grille : Grille

    init {
        this.style = "-fx-background-color: #5293c4"
        initialiserInterface()

        grille = Grille(modele)

        val scrollPane = ScrollPane(grille.setupInGrid(body))
        this.center = scrollPane
    }

    private fun initialiserInterface() {
        // Partie questions/réponses (haut)
        val vBoxPerso1 = VBox(10.0, question, boutonEnvoi).apply {
            alignment = Pos.TOP_LEFT
            padding = Insets(10.0)
        }

        val vBoxPerso2 = VBox(10.0, reponseQuestion).apply {
            alignment = Pos.TOP_LEFT
            padding = Insets(10.0)
        }

        val hBoxOuiNon = HBox(10.0, boutonOui, boutonNon).apply {
            alignment = Pos.CENTER_LEFT
        }

        val vBoxAdv = VBox(10.0, questionAdv, hBoxOuiNon).apply {
            alignment = Pos.TOP_LEFT
            padding = Insets(10.0)
        }

        val hBoxTop = HBox(20.0, vBoxPerso1, vBoxPerso2, vBoxAdv).apply {
            alignment = Pos.TOP_LEFT
            padding = Insets(10.0)
        }

        // Partie personnages (droite)
        val vBoxRight = VBox(20.0).apply {
            children.addAll(
                VBox(10.0).apply {
                    children.addAll(labelNomPersoChoisi, persoChoisi)
                    alignment = Pos.TOP_CENTER
                    padding = Insets(10.0)
                    minWidth = 200.0
                },
                VBox(10.0).apply {
                    children.addAll(labelNomPersoSuspect, persoSuspect, boutonValiderSuspect)
                    alignment = Pos.TOP_CENTER
                    padding = Insets(10.0)
                    minWidth = 200.0
                }
            )
            alignment = Pos.TOP_CENTER
            padding = Insets(20.0)
            style = "-fx-background-color: white; -fx-background-radius: 5;"
            minWidth = 200.0
            prefWidth = 200.0
        }

        // Placement dans le BorderPane
        this.top = hBoxTop
        this.right = vBoxRight

        // Ajout des marges
        setMargin(hBoxTop, Insets(20.0))
        setMargin(vBoxRight, Insets(20.0))
    }

}
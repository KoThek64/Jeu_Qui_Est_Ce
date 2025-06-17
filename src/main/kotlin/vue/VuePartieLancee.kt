package vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class VuePartieLancee : BorderPane() {
    private val body = HBox()

    // PARTIE DU HAUT
    private val question : TextArea = TextArea("").apply {
        promptText = "Posez votre question ici..."
        prefWidth = 325.0
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
    }

    private val reponseQuestion : TextArea = TextArea("").apply {
        promptText = "La réponse à votre question apparaîtra ici..."
        prefHeight = 100.0
        prefWidth = 150.0
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        isEditable = false
    }

    private val boutonEnvoi : Button = Button("Envoyer").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
    }

    private val questionAdv : TextArea = TextArea("").apply {
        promptText = "La question de l'adversaire apparaîtra ici..."
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        isEditable = false
    }

    private val boutonOui : Button = Button("OUI").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
    }
    private val boutonNon : Button = Button("NON").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 14.0)
    }

    //PARTIE DE DROITE
    private val persoChoisi : Button = Button("")

    private var labelNomPersoChoisi : Label = Label("")

    private val persoSuspect : Button = Button("")

    private var labelNomPersoSuspect : Label = Label("")


    init {
        this.style = "-fx-background-color: #5293c4"

        val vBoxPerso1 = VBox( question, boutonEnvoi).apply {
            alignment = Pos.TOP_LEFT
            padding = Insets(10.0)
        }

        val vBoxPerso2 = VBox(reponseQuestion).apply {
            alignment = Pos.TOP_CENTER
            padding = Insets(10.0)
        }

        val hBoxOuiNon = HBox(boutonOui, boutonNon)

        val vBoxAdv = VBox(questionAdv, hBoxOuiNon).apply {
            alignment = Pos.TOP_RIGHT
            padding = Insets(10.0)
        }

        val vBoxAllTop = HBox(vBoxPerso1,vBoxPerso2,vBoxAdv).apply {
            alignment = Pos.TOP_CENTER
            padding = Insets(10.0)
        }

        body.children.addAll(vBoxAllTop)
        this.center = body
    }
}
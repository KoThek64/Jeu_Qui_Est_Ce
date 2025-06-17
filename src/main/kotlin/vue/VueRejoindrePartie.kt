package vue

import javafx.beans.binding.Bindings
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.*
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import modele.Modele
import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import modele.Partie

class VueRejoindrePartie(private val modele: Modele) : BorderPane() {
    private val header = GridPane()
    private val body = HBox(40.0)
    val boutonRetour : Button

    val label = Label("Entrez l'ID de la partie que vous voulez rejoindre").apply {
        font = Font.font("Arial", FontWeight.NORMAL, 20.0)
    }
    val chooseGameID = TextField()
    val boutonValider = Button("VALIDER").apply {
        font = Font.font("Arial", FontWeight.BOLD, 26.0)
        style = "-fx-background-color: green; -fx-text-fill: white;"
    }

    init {
        this.style = "-fx-background-color: #DDA0DD"

        // --- Header ---
        header.alignment = Pos.CENTER
        val labelTop = Label("QUI-EST-CE")
        labelTop.font = Font.font("Arial", FontWeight.BLACK, 72.0)
        labelTop.textFill = Color.WHITE
        labelTop.padding = Insets(20.0)

        boutonRetour = Button("←").apply {
            font = Font.font("Arial", FontWeight.BOLD, 24.0)
            style = "-fx-background-color: white; -fx-text-fill: #DDA0DD;"
        }

        val dropShadow = DropShadow(20.0, Color.rgb(255, 0, 255, 0.7))
        labelTop.effect = dropShadow
        header.add(boutonRetour, 0, 0)
        header.add(labelTop, 1, 0)
        GridPane.setHalignment(labelTop, HPos.CENTER)
        GridPane.setHgrow(labelTop, Priority.ALWAYS)
        header.columnConstraints.addAll(
            ColumnConstraints().apply { halignment = HPos.LEFT; minWidth = 80.0 },
            ColumnConstraints().apply { hgrow = Priority.ALWAYS }
        )
        this.top = header

        // --- Partie gauche : TextArea ---
        val textAreaParties = TextArea().apply {
            font = Font.font("Arial", FontWeight.NORMAL, 36.0)
        }
        textAreaParties.text = listOf(modele.getListeParties()).joinToString("\n")
        textAreaParties.isEditable = false
        textAreaParties.prefWidth = 20.0
        textAreaParties.prefHeight = 750.0
        textAreaParties.style =
            "-fx-font-size: 32px; -fx-control-inner-background: white;"; "-fx-control-inner-background: white;"

        val titleLeft = Label("Parties disponibles :")
        titleLeft.font = Font.font("Arial", FontWeight.BOLD, 20.0)
        titleLeft.textFill = Color.BLACK

        val leftBox = VBox(15.0, titleLeft, textAreaParties).apply {
            prefWidth = 200.0
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        leftBox.alignment = Pos.TOP_CENTER
        leftBox.padding = Insets(20.0)

        // --- Partie droite : champ de saisie + bouton ---
        chooseGameID.promptText = "Entrez l'ID ici..."
        chooseGameID.font = Font.font("Arial", FontWeight.NORMAL, 14.0)
        chooseGameID.maxWidth = 200.0

        boutonValider.isVisible = false
        boutonValider.managedProperty().bind(boutonValider.visibleProperty())

        // Rendre visible le bouton seulement si on tape quelque chose
        boutonValider.visibleProperty().bind(
            Bindings.createBooleanBinding(
                { chooseGameID.text.trim().isNotEmpty() },
                chooseGameID.textProperty()
            )
        )

        // Contrôleur MVC pour le bouton Valider
        // Le stage doit être transmis lors de l'initialisation de la vue
        boutonValider.onAction = null // Nettoyage éventuel

        val rightBox = VBox(20.0, label, chooseGameID, boutonValider).apply {
            prefWidth = 400.0
            maxWidth = Double.MAX_VALUE
            HBox.setHgrow(this, Priority.ALWAYS)
        }
        rightBox.alignment = Pos.CENTER
        rightBox.padding = Insets(20.0)

        body.children.addAll(leftBox, rightBox)
        body.alignment = Pos.CENTER
        this.center = body
    }


    fun fixeControleurBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }
}
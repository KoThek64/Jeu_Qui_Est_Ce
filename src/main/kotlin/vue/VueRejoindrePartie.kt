package vue

import javafx.beans.binding.Bindings
import javafx.geometry.*
import javafx.scene.control.*
import javafx.scene.effect.DropShadow
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class VueRejoindrePartie : BorderPane() {
    private val header = GridPane()
    private val body = HBox(50.0)

    val label = Label("Entrez l'ID de la partie que vous voulez rejoindre")
    val chooseGameID = TextField()
    val boutonValider = Button("VALIDER")

    init {
        this.style = "-fx-background-color: #DDA0DD"

        // --- Header ---
        header.alignment = Pos.CENTER
        val labelTop = Label("QUI-EST-CE")
        labelTop.font = Font.font("Arial", FontWeight.BLACK, 72.0)
        labelTop.textFill = Color.WHITE
        labelTop.padding = Insets(20.0)

        val dropShadow = DropShadow(20.0, Color.rgb(255, 0, 255, 0.7))
        labelTop.effect = dropShadow
        header.add(labelTop, 0, 0)
        this.top = header

        // --- Partie gauche : TextArea pour lister les parties disponibles ---
        val textAreaParties = TextArea()
        textAreaParties.text = listOf("111111", "157967", "138795", "984632", "254687", "121478").joinToString("\n")
        textAreaParties.isEditable = false
        textAreaParties.prefColumnCount = 10
        textAreaParties.prefRowCount = 10
        textAreaParties.style = "-fx-font-size: 16px;"

        val leftBox = VBox(textAreaParties)
        leftBox.alignment = Pos.CENTER
        leftBox.padding = Insets(20.0)

        // --- Partie droite : champ de saisie + bouton ---
        chooseGameID.promptText = "Entrez l'ID ici..."
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

        val rightBox = VBox(15.0, label, chooseGameID, boutonValider)
        rightBox.alignment = Pos.CENTER
        rightBox.padding = Insets(20.0)

        body.children.addAll(leftBox, rightBox)
        body.alignment = Pos.CENTER
        this.center = body
    }
}
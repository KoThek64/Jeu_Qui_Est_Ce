package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.layout.BorderPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment

class VueGagner: BorderPane() {

    private val body: GridPane
    val boutonRetour : Button

    init {
        this.body = GridPane()

        this.style = "-fx-background-color: #03c35b"

        // Debut header
        body.alignment = Pos.CENTER

        val labelTop = Label("VOUS AVEZ GAGNÉ  \uD83D\uDE00 !")
        body.add(labelTop, 1, 1)

        labelTop.font = Font.font("Arial", FontWeight.BLACK, 72.0)
        labelTop.textFill = Color.WHITE
        labelTop.padding = Insets(20.0)

        this.center = body


        boutonRetour = Button("Retour menu").apply {
            font = Font.font("Arial", FontWeight.BOLD, 24.0)
            style = "-fx-background-color: white; -fx-text-fill: green;"
        }

        val footer = HBox(boutonRetour).apply {
            alignment = Pos.CENTER
            padding = Insets(40.0)
        }

        this.bottom = footer
    }
    fun fixeControleurBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }
}
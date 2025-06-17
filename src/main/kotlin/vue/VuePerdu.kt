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

class VuePerdu: BorderPane() {

    private val body : GridPane

    init {
        this.body = GridPane()

        this.style = "-fx-background-color: #FF0000"

        // Debut header
        body.alignment = Pos.CENTER

        val labelTop = Label("VOUS AVEZ PERDU \uD83D\uDE2D !")
        body.add(labelTop, 1, 1)

        labelTop.font = Font.font("Arial", FontWeight.BLACK, 72.0)
        labelTop.textFill = Color.WHITE
        labelTop.padding = Insets(20.0)

        this.center = body


        // Footer avec bouton retour
        val footer = HBox()
        footer.alignment = Pos.CENTER
        footer.padding = Insets(30.0)

        val boutonRetour = Button("Retour au menu")
        boutonRetour.font = Font.font("Arial", FontWeight.BOLD, 24.0)
        boutonRetour.style = "-fx-background-color: white; -fx-text-fill: #FF0000; -fx-padding: 10 20 10 20;"

        boutonRetour.setOnAction()
        {
            this.scene.root = VueCreateJoinGame()
        }

        footer.children.add(boutonRetour)
        this.bottom = footer
        boutonRetour.font = Font.font("Arial", FontWeight.BOLD, 24.0)
        boutonRetour.style = "-fx-background-color: white; -fx-text-fill: #FF0000; -fx-padding: 10 20 10 20;"

// Action au clic
        boutonRetour.setOnAction {
            this.scene.root = VueCreateJoinGame()
        }

// Changement d'opacité au survol
        boutonRetour.setOnMouseEntered {
            boutonRetour.opacity = 0.7
        }

        boutonRetour.setOnMouseExited {
            boutonRetour.opacity = 1.0
        }



    }
}
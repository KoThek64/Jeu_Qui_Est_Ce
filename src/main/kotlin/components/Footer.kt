package components

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class Footer(txt: String = "") : GridPane() {
    val labelBottom: Label
    val validateButton: Button
    
    init {
        this.alignment = Pos.CENTER

        val hbox = HBox(20.0)
        hbox.alignment = Pos.CENTER

        labelBottom = Label("Choisissez votre personnage")
        labelBottom.font = Font.font("Arial", FontWeight.BLACK, 24.0)
        labelBottom.textFill = Color.WHITE
        labelBottom.padding = Insets(20.0)
        labelBottom.effect = DropShadow().apply {
            radius = 10.0
            offsetX = 0.0
            offsetY = 0.0
            color = Color.rgb(255, 0, 255, 0.5)
        }

        validateButton = Button("Valider").apply {
            style = """
                -fx-background-color: #4CAF50;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-padding: 10 20;
                -fx-background-radius: 5;
                -fx-cursor: hand;
            """.trimIndent()
            
            isDisable = true // Désactivé par défaut
            
            setOnMouseEntered {
                if (!isDisable) {
                    style += "-fx-background-color: #45a049;"
                }
            }
            
            setOnMouseExited {
                if (!isDisable) {
                    style = style.replace("-fx-background-color: #45a049;", "-fx-background-color: #4CAF50;")
                }
            }
        }

        hbox.children.addAll(labelBottom, validateButton)

        this.add(hbox, 1, 1)
    }
    
    fun updateText(newText: String) {
        labelBottom.text = newText
        validateButton.isDisable = newText == "Choisissez votre personnage"
    }
}
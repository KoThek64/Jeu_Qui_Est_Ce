package components

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.layout.GridPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class Header : GridPane() {
    init {
        this.alignment = Pos.CENTER

        val labelTop = Label("QUI-EST-CE")
        this.add(labelTop, 1, 1)

        labelTop.font = Font.font("Arial", FontWeight.BLACK, 72.0)
        labelTop.textFill = Color.WHITE
        labelTop.padding = Insets(20.0)

        val dropShadow = DropShadow()
        dropShadow.radius = 20.0
        dropShadow.offsetX = 0.0
        dropShadow.offsetY = 0.0
        dropShadow.color = Color.rgb(255, 0, 255, 0.7)
        labelTop.effect = dropShadow
    }
}

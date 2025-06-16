package vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
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

class VueAllGames : BorderPane() {

    private var header : GridPane
    private var body : GridPane
//    val games : TextField
    val label : Label = Label("Entrez l'ID de la partie que vous voulez rejoindre")
    val chooseGameID : TextField = TextField("")
    val boutonValider : Button = Button("")

    init {
        this.header = GridPane()
        this.body = GridPane()

        this.style = "-fx-background-color: #DDA0DD"

        //Debut Header
        header.alignment = Pos.CENTER

        val labelTop = Label("QUI-EST-CE")
        header.add(labelTop, 1, 1)

        labelTop.font = Font.font("Arial", FontWeight.BLACK, 72.0)
        labelTop.textFill = Color.WHITE
        labelTop.padding = Insets(20.0)

        val dropShadow = DropShadow()
        dropShadow.radius = 20.0
        dropShadow.offsetX = 0.0
        dropShadow.offsetY = 0.0
        dropShadow.color = Color.rgb(255, 0, 255, 0.7)
        labelTop.effect = dropShadow

        this.top = header
        //Fin Header

        //Debut body
        val col1Body = ColumnConstraints().apply { hgrow = Priority.SOMETIMES }

        val col2Body = ColumnConstraints().apply {
            hgrow = Priority.ALWAYS
            halignment = HPos.CENTER
        }

        val col3Body = ColumnConstraints().apply { hgrow = Priority.SOMETIMES }
        body.columnConstraints.addAll(col1Body, col2Body, col3Body)

        val row1Body = RowConstraints().apply {
            vgrow = Priority.SOMETIMES
            valignment = VPos.CENTER
        }

        val row2Body = RowConstraints().apply { vgrow = Priority.SOMETIMES }

        val row3Body = RowConstraints().apply {
            vgrow = Priority.SOMETIMES
            valignment = VPos.CENTER
        }

        body.rowConstraints.addAll(row1Body, row2Body, row3Body)
        body.alignment = Pos.CENTER

        


    }

}
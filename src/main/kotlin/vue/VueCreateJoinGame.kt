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
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class VueCreateJoinGame: BorderPane() {

    private val header : GridPane
    private val body : GridPane
    val createGameButton: Button
    val joinGameButton: Button
    val rulesButton: Button

    init {
        this.header = GridPane()
        this.body = GridPane()

        this.style = "-fx-background-color: #DDA0DD"

        // Debut header
        val col1 = ColumnConstraints()
        col1.hgrow = Priority.SOMETIMES
        val col2 = ColumnConstraints()
        col2.hgrow = Priority.ALWAYS
        col2.halignment = HPos.CENTER
        val col3 = ColumnConstraints()
        col3.hgrow = Priority.SOMETIMES

        header.columnConstraints.addAll(col1, col2, col3)

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
        // Fin header

        // Debut Body
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

        createGameButton = Button("Créer une partie").apply {
            style = """
                -fx-background-color: #4BD7D7;
                -fx-background-radius: 30;
                -fx-text-fill: white;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                -fx-padding: 15px 40px;
                -fx-effect: dropshadow(gaussian, rgba(75, 215, 215, 0.6), 20, 0, 0, 0);
            """.trimIndent()

            val defaultStyle = this.style

            onMouseEntered = EventHandler {
                style = """
                    $defaultStyle
                    -fx-background-color: #999999;
                """.trimIndent()
            }
            onMouseExited = EventHandler {
                style = defaultStyle
            }
            onMousePressed = EventHandler {
                style = """
                    $defaultStyle
                    -fx-background-color: #AAAAAA;
                """.trimIndent()
            }
            onMouseReleased = EventHandler {
                style = defaultStyle
            }
        }

        joinGameButton = Button("Rejoindre une partie").apply {
            style = """
                -fx-background-color: #4BD7D7;
                -fx-background-radius: 30;
                -fx-text-fill: white;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                -fx-padding: 15px 40px;
                -fx-effect: dropshadow(gaussian, rgba(75, 215, 215, 0.6), 20, 0, 0, 0);
            """.trimIndent()

            val defaultStyle = this.style

            onMouseEntered = EventHandler {
                style = """
                    $defaultStyle
                    -fx-background-color: #999999;
                """.trimIndent()
            }
            onMouseExited = EventHandler {
                style = defaultStyle
            }
            onMousePressed = EventHandler {
                style = """
                    $defaultStyle
                    -fx-background-color: #AAAAAA;
                """.trimIndent()
            }
            onMouseReleased = EventHandler {
                style = defaultStyle
            }
        }

        rulesButton = Button("Règles").apply {
            style = """
                -fx-background-color: #E0E0E0;
                -fx-background-radius: 20;
                -fx-text-fill: black;
                -fx-font-size: 16px;
                -fx-font-weight: normal;
                -fx-padding: 10px 25px;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
            """.trimIndent()

            val defaultStyle = this.style

            onMouseEntered = EventHandler {
                style = """
                    $defaultStyle
                    -fx-background-color: #EEEEEE;
                """.trimIndent()
            }
            onMouseExited = EventHandler {
                style = defaultStyle
            }
            onMousePressed = EventHandler {
                style = """
                    $defaultStyle
                    -fx-background-color: #DDDDDD;
                """.trimIndent()
            }
            onMouseReleased = EventHandler {
                style = defaultStyle
            }
        }

        val topButtonsHBox = HBox(20.0).apply {
            alignment = Pos.CENTER
            children.addAll(createGameButton, joinGameButton)
        }

        body.add(topButtonsHBox, 1, 1)
        body.add(rulesButton, 1, 2)

        this.center = body
        // Fin body
    }

    /**
     * ajoute un action listener au bouton bouton
     * @param bouton le bouton cible
     * @param action le listener à ajouter
     */
    fun fixeControleurBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }

}
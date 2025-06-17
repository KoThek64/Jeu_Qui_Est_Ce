package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.effect.DropShadow
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight

class VueCreatePlayer : BorderPane() {

    private val header: GridPane = GridPane()
    private val body: GridPane = GridPane()
    val createAccount: Button
    val alreadyAccount: Button

    val prenom: TextField = TextField()
    val nom: TextField = TextField()

    init {
        this.style = "-fx-background-color: #DDA0DD"

        // Header
        val col1 = ColumnConstraints().apply { hgrow = Priority.SOMETIMES }
        val col2 = ColumnConstraints().apply {
            hgrow = Priority.ALWAYS
            halignment = HPos.CENTER
        }
        val col3 = ColumnConstraints().apply { hgrow = Priority.SOMETIMES }
        header.columnConstraints.addAll(col1, col2, col3)
        header.alignment = Pos.CENTER

        val labelTop = Label("QUI-EST-CE").apply {
            font = Font.font("Arial", FontWeight.BLACK, 72.0)
            textFill = Color.WHITE
            effect = DropShadow().apply {
                radius = 20.0
                offsetX = 0.0
                offsetY = 0.0
                color = Color.rgb(255, 0, 255, 0.7)
            }
        }
        header.add(labelTop, 1, 1)
        this.top = header

        // Body
        body.style = """
            -fx-background-color: #F3F3EA;
            -fx-background-radius: 25;
            -fx-border-radius: 25;
        """.trimIndent()

        val leftPane = VBox()
        val rightPane = VBox()
        val bottomPane = HBox()
        leftPane.minWidthProperty().bind(this.widthProperty().multiply(0.1))
        rightPane.minWidthProperty().bind(this.widthProperty().multiply(0.1))
        bottomPane.minHeightProperty().bind(this.heightProperty().multiply(0.15))
        header.minHeightProperty().bind(this.heightProperty().multiply(0.25))

        this.left = leftPane
        this.right = rightPane
        this.bottom = bottomPane

        body.alignment = Pos.CENTER

        prenom.promptText = "prénom"
        prenom.style = fieldStyle()

        nom.promptText = "nom"
        nom.style = fieldStyle()

        createAccount = boutonStyle("Créer un compte")
        alreadyAccount = boutonStyle("J'ai déjà un compte")

        val bodyButtons = VBox(30.0, prenom, nom, alreadyAccount, createAccount).apply {
            alignment = Pos.CENTER
        }

        body.add(bodyButtons, 0, 0)
        this.center = body
    }

    private fun fieldStyle() = """
        -fx-background-color: white;
        -fx-background-radius: 20;
        -fx-font-size: 18px;
        -fx-padding: 10px 15px;
        -fx-alignment: center;
    """.trimIndent()

    private fun boutonStyle(text: String) = Button(text).apply {
        style = """
            -fx-background-color: #FFFFFF;
            -fx-background-radius: 30;
            -fx-font-size: 20px;
            -fx-font-weight: bold;
            -fx-padding: 10px 20px;
        """.trimIndent()

        val defaultStyle = this.style
        onMouseEntered = EventHandler { style = "$defaultStyle -fx-background-color: #EEEEEE;" }
        onMouseExited = EventHandler { style = defaultStyle }
        onMousePressed = EventHandler { style = "$defaultStyle -fx-background-color: #DDDDDD;" }
        onMouseReleased = EventHandler { style = defaultStyle }
    }

    fun fixeControleurBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }
}

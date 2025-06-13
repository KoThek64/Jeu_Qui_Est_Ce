package vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
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


class VueCreatePlayer : BorderPane() {

    private val header : GridPane
    private val body : GridPane

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

        val dropShadow = DropShadow()
        dropShadow.radius = 20.0
        dropShadow.offsetX = 0.0
        dropShadow.offsetY = 0.0
        dropShadow.color = Color.rgb(255, 0, 255, 0.7)
        labelTop.effect = dropShadow

        this.top = header
        // Fin header

        // Debut body
        body.style = """
            -fx-background-color: #F3F3EA;
            -fx-background-radius: 25;
            -fx-border-radius: 25;
        """.trimIndent()

        // Vbox vides pour centrer le body
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
        //

        // Debut body
        body.alignment = Pos.CENTER

        val prenomButton = TextField()
        prenomButton.style = """
            -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-font-size: 18px;
                -fx-padding: 10px 15px;
                -fx-alignment: center;
        """.trimIndent()
        prenomButton.promptText = "prénom"

        val nomButton = TextField()
        nomButton.style = """
            -fx-background-color: white;
                -fx-background-radius: 20;
                -fx-font-size: 18px;
                -fx-padding: 10px 15px;
                -fx-alignment: center;
        """.trimIndent()
        nomButton.promptText = "nom"

        val createAccount = Button("Créer un compte").apply {
            style = """
                -fx-background-color: #FFFFFF;
                -fx-background-radius: 30;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                -fx-padding: 10px 20px;
            """.trimIndent()
        }

        val alreadyAccount = Button("J'ai déjà un compte").apply {
            style = """
                -fx-background-color: #FFFFFF;
                -fx-background-radius: 30;
                -fx-font-size: 20px;
                -fx-font-weight: bold;
                -fx-padding: 10px 20px;
            """.trimIndent()
        }

        val bodyButtons = VBox().apply {
            alignment = Pos.CENTER
            children.addAll(prenomButton, nomButton, alreadyAccount, createAccount)
            spacing = 30.0
        }

        body.add(bodyButtons, 0, 0)

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
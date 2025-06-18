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
import modele.Modele

class VueCreateJoinGame(
    private val modele: Modele
): BorderPane() {

    private val header : GridPane = GridPane()
    private val body : GridPane = GridPane()
    val createGameButton: Button
    val joinGameButton: Button
    val rulesButton: Button
    val backButton: Button

    init {
        this.style = "-fx-background-color: #DDA0DD"

        // Debut header
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

        // Body
        body.alignment = Pos.CENTER

        createGameButton = boutonStyle("Créer une partie")
        joinGameButton = boutonStyle("Rejoindre une partie")
        rulesButton = boutonStyle2("Règles")
        backButton = boutonStyle2("Retour")

        this.baseVue()

        this.center = body
        // Fin body
    }

    private fun boutonStyle(text: String) = Button(text).apply {
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
        onMouseEntered = EventHandler { style = "$defaultStyle -fx-background-color: #999999;".trimIndent() }
        onMouseExited = EventHandler { style = defaultStyle }
        onMousePressed = EventHandler { style = "$defaultStyle -fx-background-color: #AAAAAA".trimIndent() }
        onMouseReleased = EventHandler { style = defaultStyle }
    }

    private fun boutonStyle2(text: String) = Button(text).apply {
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
        onMouseEntered = EventHandler { style = "$defaultStyle -fx-background-color: #EEEEEE;".trimIndent() }
        onMouseExited = EventHandler { style = defaultStyle }
        onMousePressed = EventHandler {style = "$defaultStyle -fx-background-color: #DDDDDD;".trimIndent() }
        onMouseReleased = EventHandler { style = defaultStyle }
    }

    /**
     * ajoute un action listener au bouton bouton
     * @param bouton le bouton cible
     * @param action le listener à ajouter
     */
    fun fixeControleurBouton(bouton: Button, action: EventHandler<ActionEvent>) {
        bouton.onAction = action
    }

    fun baseVue() {
        body.children.clear()
        body.columnConstraints.clear()
        body.rowConstraints.clear()

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

        val topButtonsHBox = HBox(20.0).apply {
            alignment = Pos.CENTER
            children.addAll(createGameButton, joinGameButton)
        }

        body.add(topButtonsHBox, 1, 1)
        body.add(rulesButton, 1, 2)

    }

    fun rulesVue() {

        body.children.clear()
        body.columnConstraints.clear()
        body.rowConstraints.clear()

        val rulesBox = VBox()

        val rulesTitle = Text("Règles du jeu").apply {
            font = Font.font("Arial", FontWeight.BLACK, 48.0)
            fill = Color.BLACK
        }

        val rulesContent = Text(
            "Les règles du jeu sont simples, deviner le personnage choisi par l'autre joueur " +
                    "avant qu'il devine le vôtre, chacun votre tour vous allez poser des " +
                    "questions qui peuvent vous aider à trouver et il fera de même pour réussir " +
                    "avant vous.\n" +
                    "Soyez malins et bonne chance à vous !"
        ).apply {
            font = Font.font("Arial", FontWeight.NORMAL, 24.0)
            fill = Color.BLACK
            wrappingWidth = 700.0
            textAlignment = TextAlignment.CENTER
        }

        rulesBox.alignment = Pos.CENTER
        rulesBox.spacing = 40.0

        rulesBox.children.addAll(rulesTitle, rulesContent, backButton)
        body.children.add(rulesBox)
    }

    fun attenteJoueur2() {

        body.children.clear()
        body.columnConstraints.clear()
        body.rowConstraints.clear()

        val box = VBox()

        val textAttente = Text("En attente d'un second joueur").apply {
            font = Font.font("Arial", FontWeight.BLACK, 48.0)
            fill = Color.BLACK
        }

        val textIdPartie = Text("ID de la partie : ${modele.partieEnCours!!.id}").apply {
            font = Font.font("Arial", FontWeight.NORMAL, 24.0)
            fill = Color.BLACK
            textAlignment = TextAlignment.CENTER
        }

        box.alignment = Pos.CENTER
        box.spacing = 20.0

        box.children.addAll(textAttente, textIdPartie)
        body.children.add(box)
    }
}
package vue

import components.CartePersonnage
import components.Footer
import components.Header
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import modele.Grille
import info.but1.sae2025.data.Personnage
import javafx.scene.layout.VBox


class VueGame: BorderPane() {

    private val header: Header
    private val body: GridPane
    private val rightPanel: GridPane
    private val footer: Footer
    private val characterGridPane: GridPane
    private var selectedCharacter: Personnage? = null

    init {
        this.header = Header()
        this.body = GridPane()
        this.rightPanel = GridPane()
        this.footer = Footer("Choisissez votre personnage")
        this.characterGridPane = GridPane()

        this.style = "-fx-background-color: #DDA0DD;"

        this.top = header
        this.bottom = footer

        setupBody()
    }

    private fun setupBody() {
        body.style = "-fx-background-color: #DDA0DD;"
        body.alignment = Pos.CENTER
        body.padding = Insets(20.0)

        val bodyColumns = List(3) {
            ColumnConstraints().apply {
                when(it) {
                    0, 2 -> hgrow = Priority.SOMETIMES
                    1 -> {
                        hgrow = Priority.ALWAYS
                        halignment = HPos.CENTER
                    }
                }
            }
        }
        body.columnConstraints.addAll(bodyColumns)

        val bodyRows = List(3) {
            RowConstraints().apply {
                when(it) {
                    0, 2 -> vgrow = Priority.SOMETIMES
                    1 -> {
                        vgrow = Priority.ALWAYS
                        valignment = VPos.CENTER
                    }
                }
            }
        }
        body.rowConstraints.addAll(bodyRows)

        setupCharacterGrid()

        body.add(characterGridPane, 1, 1)
        this.center = body
    }

    private fun setupCharacterGrid() {
        characterGridPane.apply {
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0)
            style = """
                -fx-background-color: white;
                -fx-background-radius: 15;
                -fx-padding: 15;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 0);
            """.trimIndent()
            alignment = Pos.CENTER
        }
    }


    private fun createCharacterCard(personnage: Personnage, col: Int, row: Int): VBox {
        val carte = CartePersonnage(personnage, col, row)

        val imageView = ImageView(Image(carte.urlComplet)).apply {
            fitWidth = 100.0
            fitHeight = 100.0
            isPreserveRatio = true
        }

        val nameLabel = Label("${carte.prenom} ${carte.nom}").apply {
            style = """
            -fx-font-size: 14px;
            -fx-font-weight: bold;
            -fx-text-alignment: center;
        """.trimIndent()
        }

        return VBox(10.0, imageView, nameLabel).apply {
            alignment = Pos.CENTER
            style = """
            -fx-background-color: white;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
        """.trimIndent()

            setOnMouseEntered {
                if (selectedCharacter != personnage) {
                    style += "-fx-background-color: #f0f0f0;"
                }
            }

            setOnMouseExited {
                if (selectedCharacter != personnage) {
                    style = style.replace("-fx-background-color: #f0f0f0;", "-fx-background-color: white;")
                }
            }

            setOnMouseClicked {
                // Désélectionner la carte précédemment sélectionnée
                characterGridPane.children.forEach { node ->
                    if (node is VBox) {
                        node.style = """
                        -fx-background-color: white;
                        -fx-padding: 10;
                        -fx-background-radius: 10;
                        -fx-border-radius: 10;
                        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
                    """.trimIndent()
                    }
                }

                // Sélectionner la nouvelle carte
                selectedCharacter = personnage
                style = """
                -fx-background-color: #f0f0f0;
                -fx-padding: 10;
                -fx-background-radius: 10;
                -fx-border-radius: 10;
                -fx-border-color: #4BD7D7;
                -fx-border-width: 3;
                -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
            """.trimIndent()

                // Mettre à jour le texte du footer
                footer.updateText("Personnage sélectionné : ${personnage.prenom} ${personnage.nom}")
            }
        }
    }

    fun updateCharacterGrid(grille: Grille) {
        characterGridPane.children.clear()

        var row = 0
        var col = 0
        val maxColumns = 6

        for (ligne in grille.getPersonnages()) {
            for (personnage in ligne) {
                val characterCard = createCharacterCard(personnage, col, row)
                characterGridPane.add(characterCard, col, row)

                col++
                if (col >= maxColumns) {
                    col = 0
                    row++
                }
            }
        }
    }

    fun getSelectedCharacter(): Personnage? {
        return selectedCharacter
    }
}

package vue

import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.layout.BorderPane
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.scene.text.Text
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import modele.Grille // Importe votre classe Grille
import info.but1.sae2025.data.Personnage // Importe la classe Personnage de votre API
import javafx.event.EventHandler


class VueGame: BorderPane() {

    private val header: GridPane
    private val body: GridPane
    private val rightPanel: GridPane
    private val footer: GridPane
    private val characterGridPane: GridPane // Rendre le GridPane des personnages accessible

    init {
        this.header = GridPane()
        this.body = GridPane()
        this.rightPanel = GridPane()
        this.footer = GridPane()
        this.characterGridPane = GridPane()

        this.style = "-fx-background-color: #DDA0DD;"

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
        // Fin header

        // Debut Body (la grille des personnages)
        body.style = "-fx-background-color: #DDA0DD;"
        body.alignment = Pos.CENTER
        body.padding = Insets(20.0)

        val col1Body = ColumnConstraints().apply { hgrow = Priority.SOMETIMES }
        val col2Body = ColumnConstraints().apply {
            hgrow = Priority.ALWAYS
            halignment = HPos.CENTER
        }
        val col3Body = ColumnConstraints().apply { hgrow = Priority.SOMETIMES }
        body.columnConstraints.addAll(col1Body, col2Body, col3Body)

        val row1Body = RowConstraints().apply { vgrow = Priority.SOMETIMES }
        val row2Body = RowConstraints().apply {
            vgrow = Priority.ALWAYS
            valignment = VPos.CENTER
        }
        val row3Body = RowConstraints().apply { vgrow = Priority.SOMETIMES }
        body.rowConstraints.addAll(row1Body, row2Body, row3Body)

        // Configurer characterGridPane ici
        characterGridPane.apply {
            hgap = 10.0
            vgap = 10.0
            padding = Insets(20.0)
            style = """
                -fx-background-color: #DDA0DD;
                -fx-background-radius: 20;
            """.trimIndent()
            alignment = Pos.CENTER
        }

        body.add(characterGridPane, 1, 1) // Ajoute la grille de personnages au body

        this.center = body
        // Fin Body

        // Debut Footer (inchangé)
        footer.alignment = Pos.CENTER

        val labelBottom = Label("Choisissez votre personnage")
        footer.add(labelBottom, 1, 1)

        labelBottom.font = Font.font("Arial", FontWeight.BLACK, 24.0)
        labelBottom.textFill = Color.WHITE
        labelBottom.padding = Insets(20.0)
        labelBottom.effect = DropShadow().apply {
            radius = 10.0
            offsetX = 0.0
            offsetY = 0.0
            color = Color.rgb(255, 0, 255, 0.5)
        }

        this.bottom = footer
        // Fin Footer
    }

    /**
     * Méthode pour mettre à jour la grille de personnages avec les données réelles.
     * @param grille L'instance de Grille contenant les personnages à afficher.
     */
    fun updateCharacterGrid(grille: Grille) {
        // Supprime tous les enfants actuels pour pouvoir les recréer
        characterGridPane.children.clear()

        var colIdx = 0
        var rowIdx = 0
        val numCols = 6 // Assurez-vous que c'est cohérent avec votre design (5 colonnes par ligne)

        for (rowList in grille.getPersonnages()) { // Itère sur la liste de listes de Personnage
            for (personnage in rowList) { // Itère sur chaque Personnage
                try {
                    // Utilise l'URL du Personnage pour charger l'image
                    val characterImage = Image(personnage.url)
                    val imageView = ImageView(characterImage).apply {
                        fitWidth = 100.0 // Taille des images (ajuster selon votre design)
                        fitHeight = 100.0 // Taille des images (ajuster)
                        isPreserveRatio = true

                        // Styles et effets pour chaque vignette de personnage
                        style = """
                            -fx-background-color: white;
                            -fx-padding: 5px;
                            -fx-border-radius: 10;
                            -fx-background-radius: 10;
                            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 0);
                        """.trimIndent()

                        // Effets de survol/clic
                        val defaultStyle = this.style
                        onMouseEntered = EventHandler {
                            style = """
                                $defaultStyle
                                -fx-effect: dropshadow(gaussian, #FFC0CB, 15, 0, 0, 0); /* Glow rose */
                            """.trimIndent()
                        }
                        onMouseExited = EventHandler {
                            style = defaultStyle
                        }
                        onMousePressed = EventHandler {
                            style = """
                                $defaultStyle
                                -fx-background-color: #ADD8E6; /* Bleu clair */
                            """.trimIndent()
                        }
                        onMouseReleased = EventHandler {
                            style = defaultStyle
                        }
                    }
                    characterGridPane.add(imageView, colIdx, rowIdx)

                } catch (e: Exception) {
                    println("Erreur de chargement de l'image pour ${personnage.nom} ${personnage.prenom} depuis ${personnage.url}: ${e.message}")
                    // Optionnel: ajouter un placeholder si l'image ne peut pas être chargée
                    val placeholder = Label("Erreur image").apply {
                        prefWidth = 100.0
                        prefHeight = 100.0
                        alignment = Pos.CENTER
                        style = "-fx-background-color: #FFCCCC; -fx-border-color: red; -fx-border-width: 1;"
                    }
                    characterGridPane.add(placeholder, colIdx, rowIdx)
                }

                colIdx++
                if (colIdx >= numCols) {
                    colIdx = 0
                    rowIdx++
                }
            }
        }
    }
}

package components

import info.but1.sae2025.data.Personnage
import javafx.geometry.HPos
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.layout.ColumnConstraints
import javafx.scene.layout.GridPane
import javafx.scene.layout.Priority
import javafx.scene.layout.RowConstraints
import javafx.scene.control.Label
import modele.Modele
import modele.Grille as ModelGrille

/**
 * Composant réutilisable représentant une grille de personnages
 */
class Grille(
    private val modele: Modele
) : GridPane() {
    private var selectedCharacter: Personnage? = null
    private var onCharacterSelected: ((Personnage) -> Unit)? = null

    init {
        this.apply {
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

    /**
     * Configure la grille dans un conteneur parent avec une disposition optimisée
     * 
     * @param parentContainer Le conteneur GridPane dans lequel la grille sera ajoutée
     * @param parentBackgroundColor La couleur d'arrière-plan du conteneur parent
     * @return Le conteneur parent configuré avec la grille
     */
    fun setupInGrid(parentContainer: GridPane, parentBackgroundColor: String = "#DDA0DD"): GridPane {
        // Configuration du conteneur parent
        parentContainer.apply {
            style = "-fx-background-color: $parentBackgroundColor;"
            alignment = Pos.CENTER
            padding = Insets(20.0)
        }

        // Configuration des colonnes
        val bodyColumns = List(3) { index ->
            ColumnConstraints().apply {
                when(index) {
                    0, 2 -> hgrow = Priority.SOMETIMES
                    1 -> {
                        hgrow = Priority.ALWAYS
                        halignment = HPos.CENTER
                    }
                }
            }
        }
        parentContainer.columnConstraints.addAll(bodyColumns)

        // Configuration des lignes
        val bodyRows = List(3) { index ->
            RowConstraints().apply {
                when(index) {
                    0, 2 -> vgrow = Priority.SOMETIMES
                    1 -> {
                        vgrow = Priority.ALWAYS
                        valignment = VPos.CENTER
                    }
                }
            }
        }
        parentContainer.rowConstraints.addAll(bodyRows)

        // Ajout de cette grille au conteneur parent
        parentContainer.add(this, 1, 1)

        return parentContainer
    }

    fun updateGrid(personnages: List<List<Personnage>>?) {
        this.children.clear()

        if (personnages == null || personnages.isEmpty()) {
            println("Aucun personnage à afficher dans la grille")
            return
        }

        println("Mise à jour de la grille avec ${personnages.flatten().size} personnages")

        var row = 0
        var col = 0
        val maxColumns = 6

        for (ligne in personnages) {
            for (personnage in ligne) {
                val carte = CartePersonnage(personnage, col, row).apply {
                    setOnMouseClicked {
                        handleCharacterSelection(this, personnage)
                    }
                }

                this.add(carte, col, row)
                println("Ajout de la carte pour ${personnage.prenom} ${personnage.nom} à la position ($col, $row)")

                col++
                if (col >= maxColumns) {
                    col = 0
                    row++
                }
            }
        }
    }

    private fun handleCharacterSelection(carte: CartePersonnage, personnage: Personnage) {
        // Réinitialiser le style de toutes les cartes
        this.children.filterIsInstance<CartePersonnage>().forEach { it.resetStyle() }

        // Mettre à jour la sélection
        selectedCharacter = personnage
        carte.setSelectedStyle()

        // Notifier le changement
        onCharacterSelected?.invoke(personnage)
    }

    fun setOnCharacterSelectedListener(listener: (Personnage) -> Unit) {
        onCharacterSelected = listener
    }

    /**
     * Configure la réaction à la sélection d'un personnage en mettant à jour le footer
     * 
     * @param footer Le composant Footer à mettre à jour lors de la sélection
     */
    fun configureWithFooter(footer: Footer) {
        this.setOnCharacterSelectedListener { personnage ->
            footer.updateText("Personnage sélectionné : ${personnage.prenom} ${personnage.nom}")
        }
    }

    fun getSelectedCharacter(): Personnage? = selectedCharacter

    /**
     * Met à jour la grille avec les personnages du modèle
     *
     * @param grilleModel Le modèle de grille contenant les personnages à afficher
     */
    fun updateCharacterGrid(grilleModel: modele.Grille?) {
        if (grilleModel == null) {
            println("Attention: Grille modèle est null - aucun personnage à afficher")
            return
        }

        val personnages = grilleModel.getPersonnages()
        println("Mise à jour de la grille d'affichage avec ${personnages.flatten().size} personnages")
        grilleModel.recupererGrille(modele.partieEnCours!!.id, modele.partieEnCours!!.etat!!.idJoueur1 , modele.getClient())
        this.updateGrid(grilleModel.getPersonnages())
    }
}

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

class   Grille(
    private val modele: Modele,
    val onCharacterSelected: ((Personnage) -> Unit)? = null
) : GridPane() {
    private var selectedCharacter: Personnage? = null

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
        this.children.filterIsInstance<CartePersonnage>().forEach { it.resetStyle() }

        selectedCharacter = personnage
        carte.setSelectedStyle()

        onCharacterSelected?.invoke(personnage)
    }


    fun getSelectedCharacter(): Personnage? = selectedCharacter

    fun updateCharacterGrid(grilleModel: modele.Grille?, idJoueur: Int) {
        if (grilleModel == null) {
            println("Attention: Grille modèle est null - aucun personnage à afficher")
            return
        }

        val personnages = grilleModel.personnages
        println("Mise à jour de la grille d'affichage avec ${personnages.flatten().size} personnages")
        grilleModel.recupererGrille(modele.partieEnCours!!.id, idJoueur , modele.getClient())
        this.updateGrid(grilleModel.personnages)
    }
}
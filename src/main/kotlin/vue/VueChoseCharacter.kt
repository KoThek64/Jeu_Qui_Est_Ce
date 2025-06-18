package vue

import components.Footer
import components.Grille
import components.Header
import info.but1.sae2025.data.Personnage
import javafx.scene.control.ScrollPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import modele.Modele

/**
 * Vue pour choisir un personnage dans une grille
 */
class VueChoseCharacter(
    private val modele: Modele
): BorderPane() {

    private val header: Header = Header()
    private val body: GridPane = GridPane()
    val footer: Footer = Footer("Choisissez votre personnage")
    private val grille: Grille

    init {
        // Configuration du style global
        this.style = "-fx-background-color: #DDA0DD;"

        // Ajout des composants principaux
        this.top = header

        this.bottom = footer

        grille = Grille(modele) { character ->
            footer.updateText("Personnage sélectionné : ${character.prenom} ${character.nom}")
        }

        val scrollPane = ScrollPane(grille.setupInGrid(body))
        this.center = scrollPane
    }

    /**
     * Met à jour la grille avec les personnages du modèle
     *
     * @param grilleModel Le modèle de grille contenant les personnages à afficher
     */
    fun updateCharacterGrid(grilleModel: modele.Grille?) {
        val monId = modele.monJoueur!!.id
        val autreJoueurId = if (monId == modele.partieEnCours!!.etat!!.idJoueur1) {
            modele.partieEnCours!!.etat!!.idJoueur1
        }else {
            modele.partieEnCours!!.etat!!.idJoueur2
        }
        grille.updateCharacterGrid(grilleModel, autreJoueurId)
    }

    /**
     * Récupère le personnage actuellement sélectionné
     *
     * @return Le personnage sélectionné ou null si aucun personnage n'est sélectionné
     */
    fun getSelectedCharacter(): Personnage? = grille.getSelectedCharacter()
}
package vue

import components.Footer
import components.Grille
import components.Header
import info.but1.sae2025.data.Personnage
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane

/**
 * Vue pour choisir un personnage dans une grille
 */
class VueChoseCharacter : BorderPane() {

    private val header: Header = Header()
    private val body: GridPane = GridPane()
    private val footer: Footer = Footer("Choisissez votre personnage")
    private val grille: Grille = Grille()

    init {
        // Configuration du style global
        this.style = "-fx-background-color: #DDA0DD;"

        // Ajout des composants principaux
        this.top = header
        this.bottom = footer

        // Configuration des événements de la grille avec le footer
        grille.configureWithFooter(footer)

        // Configuration et ajout du corps de la vue avec la grille
        this.center = grille.setupInGrid(body)
    }

    /**
     * Met à jour la grille avec les personnages du modèle
     *
     * @param grilleModel Le modèle de grille contenant les personnages à afficher
     */
    fun updateCharacterGrid(grilleModel: modele.Grille?) {
        grille.updateCharacterGrid(grilleModel)
    }

    /**
     * Récupère le personnage actuellement sélectionné
     *
     * @return Le personnage sélectionné ou null si aucun personnage n'est sélectionné
     */
    fun getSelectedCharacter(): Personnage? = grille.getSelectedCharacter()
}
package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueCreateJoinGame
import vue.VueRejoindrePartie

class ControleurJoinGame(
    private val modele: Modele,
    private val vue: VueCreateJoinGame,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(p0: ActionEvent?) {
        val vueSuivante = VueRejoindrePartie(modele)
        vueSuivante.fixeControleurBouton(vueSuivante.boutonRetour, ControleurButtonBackToVueCreateJoinGame(modele,vueSuivante,stage))
        // Branche le contrôleur MVC pour le bouton Valider
        vueSuivante.boutonValider.onAction = ControleurValiderJoinGame(modele, vueSuivante, stage)

        val scene = Scene(vueSuivante, 1920.0, 1080.0)
        stage.scene = scene
    }
}

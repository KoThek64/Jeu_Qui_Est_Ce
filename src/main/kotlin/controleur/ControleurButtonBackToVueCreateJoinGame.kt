package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueCreateJoinGame
import vue.VueRejoindrePartie

class ControleurButtonBackToVueCreateJoinGame(
    private val modele: Modele,
    private val vue: VueRejoindrePartie,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val vueSuivante = VueCreateJoinGame()

        vueSuivante.fixeControleurBouton(vueSuivante.rulesButton, ControleurRules(vueSuivante))
        vueSuivante.fixeControleurBouton(vueSuivante.backButton, ControleurRulesBackButton(vueSuivante))
        vueSuivante.fixeControleurBouton(vueSuivante.createGameButton, ControleurCreateGame(modele, vueSuivante, stage))
        vueSuivante.fixeControleurBouton(vueSuivante.joinGameButton, ControleurJoinGame(modele, vueSuivante, stage))

        val scene = Scene(vueSuivante, 1920.0, 1080.0)
        stage.scene = scene
    }

}
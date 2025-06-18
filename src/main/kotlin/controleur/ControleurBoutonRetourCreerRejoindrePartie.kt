package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueCreerRejoindrePartie
import vue.VueRejoindrePartie

class ControleurBoutonRetourCreerRejoindrePartie(
    private val modele: Modele,
    private val vue: VueRejoindrePartie,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val vueSuivante = VueCreerRejoindrePartie(modele)

        vueSuivante.fixeControleurBouton(vueSuivante.rulesButton, ControleurRegles(vueSuivante))
        vueSuivante.fixeControleurBouton(vueSuivante.backButton, ControleurReglesBoutonRetour(vueSuivante))
        vueSuivante.fixeControleurBouton(vueSuivante.createGameButton, ControleurCreerPartie(modele, vueSuivante, stage))
        vueSuivante.fixeControleurBouton(vueSuivante.joinGameButton, ControleurRejoindrePartie(modele, vueSuivante, stage))

        val scene = Scene(vueSuivante, 1920.0, 1080.0)
        stage.scene = scene
    }

}
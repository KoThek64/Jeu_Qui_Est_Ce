package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueGame
import vue.VueRejoindrePartie

class ControleurBoutonValiderPartie(
    private val modele: Modele,
    private val vue: VueRejoindrePartie,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        modele.partieEnCours?.rejoindrePartie(vue.chooseGameID.text.toInt(), modele.monJoueur!!.id, modele.monJoueur!!.cle)

        val vueSuivante = VueGame(modele)

        val scene = Scene(vueSuivante, 1920.0, 1080.0)
        stage.scene = scene
    }
}
package controleur

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueChoseCharacter
import vue.VueCreateJoinGame
import vue.VueGame
import java.util.*

class ControleurCreateGame(
    private val modele: Modele,
    private val vue: VueCreateJoinGame,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        modele.creerPartie()
        vue.attenteJoueur2()

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                // On rafraîchit l'état de la partie
                modele.partieEnCours?.rafraichirEtat()

                // On vérifie si l'étape est bien INITIALISATION (adapter ci-dessous si c'est une enum ou un string)
                val etapeActuelle = modele.partieEnCours?.etat?.etape?.name // ou juste .etape si c'est un string
                if (etapeActuelle == "INITIALISATION") {
                    Platform.runLater {
                        // Passage à la vue principale du jeu
                        val vueJeu = VueChoseCharacter(modele)
                        val scene = Scene(vueJeu, 1920.0, 1080.0)

                        val selfGrille = modele.partieEnCours?.selfGrille
                        vueJeu.updateCharacterGrid(selfGrille)

                        println("${modele.partieEnCours!!.selfGrille}")
                        println("${modele.partieEnCours!!.otherGrille}")

                        stage.scene = scene
                        timer.cancel()
                    }
                }
            }
        }, 0, 1000)
    }
}
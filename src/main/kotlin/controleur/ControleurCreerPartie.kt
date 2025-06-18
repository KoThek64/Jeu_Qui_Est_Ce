package controleur

import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueChoisirPersonnage
import vue.VueCreerRejoindrePartie
import vue.VuePartieLancee
import java.util.*

class ControleurCreerPartie(
    private val modele: Modele,
    private val vue: VueCreerRejoindrePartie,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        modele.creerPartie()
        vue.attenteJoueur2()

        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {

                modele.partieEnCours?.rafraichirEtat()

                val etapeActuelle = modele.partieEnCours?.etat?.etape?.name
                if (etapeActuelle == "INITIALISATION") {
                    Platform.runLater {

                        val vueJeu = VueChoisirPersonnage(modele)
                        val scene = Scene(vueJeu, 1920.0, 1080.0)

                        val selfGrille = modele.partieEnCours?.selfGrille
                        vueJeu.updateCharacterGrid(selfGrille)

                        vueJeu.footer.validateButton.setOnAction {
                            val selectedCharacter = vueJeu.getSelectedCharacter()

                            selfGrille?.personnages?.forEachIndexed { x, row ->
                                row.forEachIndexed { y, personnage ->
                                    if (personnage == selectedCharacter) {
                                        modele.partieEnCours!!.choisirPersonnage(personnage, x, y)

                                        val gameVue = VuePartieLancee(modele)
                                        val controller = ControleuJeu(modele, gameVue, stage)
                                        val nouvelleScene = Scene(gameVue, 1920.0, 1080.0)
                                        stage.scene = nouvelleScene
                                        return@forEachIndexed
                                    }
                                }
                            }
                        }

                        stage.scene = scene
                        timer.cancel()
                    }
                }
            }
        }, 0, 1000)
    }
}
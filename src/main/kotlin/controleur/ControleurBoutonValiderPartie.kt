package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Modele
import modele.Partie
import vue.VueChoisirPersonnage
import vue.VuePartieLancee
import vue.VueRejoindrePartie

class ControleurBoutonValiderPartie(
    private val modele: Modele,
    private val vue: VueRejoindrePartie,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val idPartieStr = vue.chooseGameID.text
        if (idPartieStr.isNullOrBlank()) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Erreur"
            alert.headerText = "Veuillez entrer un ID de partie."
            alert.showAndWait()
            return
        }

        val idPartie = idPartieStr.toIntOrNull()
        if (idPartie == null) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Erreur"
            alert.headerText = "L'ID de la partie doit être un nombre."
            alert.showAndWait()
            return
        }

        val joueurId = modele.getMonJoueurId()
        val joueurCle = modele.getMonJoueurCle()

        val partie = modele.partieEnCours ?: Partie(
            modele.getClient(),
            joueurId!!,
            joueurCle!!,
            idPartie
        )

        modele.partieEnCours = partie

        val connexion = partie.rejoindrePartie(idPartie, joueurId!!, joueurCle!!)
        if (connexion !is Exception) {
            partie.rafraichirEtat()

            var vueJeu = VueChoisirPersonnage(modele)

            vueJeu.footer.validateButton.setOnAction {
                val otherGrille = modele.partieEnCours!!.otherGrille

                otherGrille.personnages.forEachIndexed {x, array ->
                    array.forEachIndexed { y, pers ->
                        if (pers == vueJeu.getSelectedCharacter()) {
                            modele.partieEnCours!!.choisirPersonnage(
                                pers, x, y
                            )

                            val gameVue = VuePartieLancee(modele)
                            val controller = ControleuJeu(modele, gameVue, stage)
                            val scene = Scene(gameVue, 1920.0, 1080.0)
                            stage.scene = scene
                            return@forEachIndexed
                        }
                    }
                }
            }

            val scene = Scene(vueJeu, 1920.0, 1080.0)

            val otherGrille = modele.partieEnCours?.otherGrille
            vueJeu.updateCharacterGrid(otherGrille)

            stage.scene = scene
        } else {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Erreur"
            alert.headerText = "La partie est complète"
            alert.showAndWait()
            return
        }
    }
}
package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Modele
import modele.Partie
import vue.VueGame
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

        val connexion = partie.rejoindrePartie(idPartie, joueurId!!, joueurCle!!)
        if (connexion !is Exception) {
            // Afficher la vue de partie lancée ou autre selon le flow
            val vuePartie = VuePartieLancee()
            // TODO : Passer les infos nécessaires à la vue partie si besoin
            stage.scene = javafx.scene.Scene(vuePartie, 1920.0, 1080.0)
        } else {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Erreur"
            alert.headerText = "La partie est complète"
            alert.showAndWait()
            return
        }



        /*
        modele.partieEnCours?.rejoindrePartie(vue.chooseGameID.text.toInt(), modele.monJoueur!!.id, modele.monJoueur!!.cle)

        val vueSuivante = VueGame(modele)

        val scene = Scene(vueSuivante, 1920.0, 1080.0)
        stage.scene = scene
         */
    }
}
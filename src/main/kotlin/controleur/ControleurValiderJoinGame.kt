package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Modele
import modele.Partie
import vue.VueRejoindrePartie
import vue.VuePartieLancee

class ControleurValiderJoinGame(
    private val modele: Modele,
    private val vue: VueRejoindrePartie,
    private val stage: Stage
) : EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent?) {
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
        if (joueurId == null || joueurCle == null) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Erreur"
            alert.headerText = "Aucun joueur inscrit. Veuillez créer un compte."
            alert.showAndWait()
            return
        }
        val partie = modele.getPartieEnCours() ?: Partie(
            modele.getClient(),
            joueurId,
            joueurCle,
            idPartie
        )
        partie.rejoindrePartie(idPartie, joueurId, joueurCle)
        // Afficher la vue de partie lancée ou autre selon le flow
        val vuePartie = VuePartieLancee()
        // TODO : Passer les infos nécessaires à la vue partie si besoin
        stage.scene = javafx.scene.Scene(vuePartie, 1920.0, 1080.0)
    }
}

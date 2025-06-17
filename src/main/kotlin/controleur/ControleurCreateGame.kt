package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import modele.Modele
import vue.VueCreateJoinGame

class ControleurCreateGame(
    private val modele: Modele,
    private val vue: VueCreateJoinGame,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        // 1. Tente de créer la partie via le modèle
        val creation = modele.creerPartie()
        if (!creation) {
            val alert = Alert(Alert.AlertType.ERROR)
            alert.title = "Erreur"
            alert.headerText = "Impossible de créer la partie"
            alert.contentText = "Vérifiez que vous êtes bien inscrit."
            alert.showAndWait()
            return
        }
        // 2. Récupère la partie créée
        val partie = modele.getPartieEnCours()!!
        // 3. Affiche l'écran d'attente de joueur
        val partieIdLabel = Label("ID de la partie : ${partie.id}")
        partieIdLabel.style = "-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: purple;"
        val waitingLabel = Label("En attente de joueurs... (1/2)")
        val vbox = VBox(20.0, partieIdLabel, waitingLabel)
        vbox.alignment = Pos.CENTER
        vue.center = vbox
        // 4. Lancer la logique d'attente (thread ou polling)
        // TODO: Créer et lancer ControleurAttentePartie ou équivalent ici si disponible
    }

}
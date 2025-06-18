package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage
import modele.Modele
import vue.VueCreerRejoindrePartie
import vue.VueCreerJoueur

class ControleurCreerJoueur(
    private val modele: Modele,
    private val vue: VueCreerJoueur,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        val nom = vue.nom.text.trim()
        val prenom = vue.prenom.text.trim()

        if (nom.isEmpty() || prenom.isEmpty()) {
            val dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.title="Erreur lors de la création du joueur"
            dialog.headerText="Veuillez entrer un prénom et un nom."
            dialog.showAndWait()
        }

        val creation = modele.inscription(nom, prenom)

        if (creation !is Exception) {
            val vueSuivante = VueCreerRejoindrePartie(modele)

            vueSuivante.fixeControleurBouton(vueSuivante.rulesButton, ControleurRegles(vueSuivante))
            vueSuivante.fixeControleurBouton(vueSuivante.backButton, ControleurReglesBoutonRetour(vueSuivante))
            vueSuivante.fixeControleurBouton(vueSuivante.createGameButton, ControleurCreerPartie(modele, vueSuivante, stage))
            vueSuivante.fixeControleurBouton(vueSuivante.joinGameButton, ControleurRejoindrePartie(modele, vueSuivante, stage))

            val scene = Scene(vueSuivante, 1920.0, 1080.0)
            stage.scene = scene
        } else {
            val dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.title="Erreur lors de la création du joueur"
            dialog.headerText="${creation.message}"
            dialog.contentText="Le compte existe déjà."
            dialog.showAndWait()
        }
    }
}

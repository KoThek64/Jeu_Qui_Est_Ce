
import controleur.ControleurAlreadyAccount
import controleur.ControleurCreatePlayer
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import modele.Grille
import vue.VueCreateJoinGame
import vue.VueCreatePlayer
import vue.VueGagner
import vue.VueGame
import vue.VuePerdu
import vue.VueRejoindrePartie

class MainQuiEstCe : Application() {
    override fun start(stage: Stage) {
        // Initialisation du client
        val client = QuiEstCeClient("localhost", 8080)
        val modele = Modele(client)

        // Création de la vue principale
        val vue = VueGame()

        // Création d'une nouvelle partie pour obtenir une grille
        if (modele.inscription("Test", "User") == null) {
            if (modele.creerPartie()) {
                // Création d'une instance de Grille
                val grille = Grille()

                // Récupération de la partie en cours depuis le modèle
                val partieEnCours = modele.getPartieEnCours()

                if (partieEnCours != null) {
                    // Récupération des données de la grille depuis le serveur
                    grille.recupererGrille(
                        partieEnCours.id,
                        partieEnCours.joueurId,
                        client
                    )

                    // Mise à jour de la grille dans la vue
                    vue.updateCharacterGrid(grille)
                }
            }
        }

        // Configuration et affichage de la fenêtre
        val scene = Scene(vue, 1920.0, 1080.0)
        stage.title = "Qui-est-ce ?"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(MainQuiEstCe::class.java)
}
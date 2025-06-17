import controleur.ControleurAlreadyAccount
import controleur.ControleurCreatePlayer
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueCreateJoinGame
import vue.VueCreatePlayer
import vue.VueGagner
import vue.VuePartieLancee
import vue.VuePerdu
import vue.VueRejoindrePartie

class MainQuiEstCe : Application() {
    override fun start(stage: Stage) {

        val client = QuiEstCeClient("localhost", 8080)
        val modele = Modele(client)

        val vue = VuePartieLancee()

//        vue.fixeControleurBouton(vue.createAccount, ControleurCreatePlayer(modele, vue, stage))
        // vue.fixeControleurBouton(vue.alreadyAccount, ControleurAlreadyAccount(modele, vue, stage))

        val scene = Scene(vue, 1920.0, 1080.0)
        stage.title = "Qui-est-ce ?"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(MainQuiEstCe::class.java)
}

import controleur.ControleurCreerJoueur
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import modele.Modele
import vue.VueCreerJoueur

class MainQuiEstCe : Application() {
    override fun start(stage: Stage) {

        val client = QuiEstCeClient("localhost", 8080)
        val modele = Modele(client)

        val vue = VueCreerJoueur()

        vue.fixeControleurBouton(vue.createAccount, ControleurCreerJoueur(modele, vue, stage))

        val scene = Scene(vue, 1920.0, 1080.0)
        stage.title = "Qui-est-ce ?"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(MainQuiEstCe::class.java)
}

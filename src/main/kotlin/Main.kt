import controleur.ControleurRules
import controleur.ControleurRulesBackButton
import vue.VueCreateJoinGame
import info.but1.sae2025.QuiEstCeClient
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class MainQuiEstCe: Application() {
    override fun start(stage: Stage) {
        val vue = VueCreateJoinGame()

        vue.fixeControleurBouton(vue.rulesButton, ControleurRules(vue))
        vue.fixeControleurBouton(vue.backButton, ControleurRulesBackButton(vue))

        val scene = Scene(vue, 1920.0, 1080.0)
        stage.title = "Qui-est-ce ?"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(MainQuiEstCe::class.java)
    println("Hello, World!")
    var client: QuiEstCeClient
    client = QuiEstCeClient("localhost", 8080)
    // configuration à modifier bien entendu
    client.requeteEssai()
}
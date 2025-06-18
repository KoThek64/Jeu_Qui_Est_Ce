package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import vue.VueCreerRejoindrePartie

class ControleurReglesBoutonRetour(private val vue: VueCreerRejoindrePartie): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        vue.baseVue()
    }
}
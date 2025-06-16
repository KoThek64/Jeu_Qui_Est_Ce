package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import vue.VueCreateJoinGame

class ControleurRulesBackButton(private val vue: VueCreateJoinGame): EventHandler<ActionEvent> {
    override fun handle(event: ActionEvent) {
        vue.baseVue()
    }
}
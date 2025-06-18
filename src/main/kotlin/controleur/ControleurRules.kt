package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import vue.VueCreateJoinGame

class ControleurRules(private val vue: VueCreateJoinGame): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        vue.rulesVue()
    }

}
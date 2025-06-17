package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import modele.Modele
import vue.VueCreateJoinGame

class ControleurCreateGame(
    private val modele: Modele,
    private val vue: VueCreateJoinGame,
    private val stage: Stage
) : EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        TODO()
    }

}
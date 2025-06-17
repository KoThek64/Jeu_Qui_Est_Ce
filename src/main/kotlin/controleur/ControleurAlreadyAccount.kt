package controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import modele.Modele
import vue.VueCreatePlayer

class ControleurAlreadyAccount(
    private val modele: Modele,
    private val vue: VueCreatePlayer,
    private val stage: Stage
): EventHandler<ActionEvent> {

    override fun handle(event: ActionEvent) {
        TODO()
    }

}
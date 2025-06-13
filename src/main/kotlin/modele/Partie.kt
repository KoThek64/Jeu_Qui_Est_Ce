package modele

import info.but1.sae2025.data.EtatPartie

class Partie(idPartie: Int) {

    private var id: Int = idPartie
    private lateinit var etat: EtatPartie

    init {
    }

    fun demarrerPartie() {
        TODO()
    }

    fun getEtat(): EtatPartie = etat


    fun terminerPartie(){
        TODO()
    }

    fun changerJoueurActif(){
        TODO()
    }
}
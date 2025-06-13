package Modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.Personnage


class Grille(personnages : List<List<Personnage>>) {
    private var personnages : List<List<Personnage>>

    init {
        this.personnages = listOf<List<Personnage>>()
    }

    fun genererGrille(idPartie: Int, idJoueur: Int, client: QuiEstCeClient){
        this.personnages = client.requeteGrilleJoueur(idPartie, idJoueur)
    }

    fun getPersonnages(): List<List<Personnage>> {
        return this.personnages
    }
}
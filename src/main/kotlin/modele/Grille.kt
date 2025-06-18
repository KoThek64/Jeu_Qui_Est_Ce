package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.Personnage


class Grille() {
    private var personnages : List<List<Personnage>>

    init {
        this.personnages = listOf<List<Personnage>>()
    }

    fun recupererGrille(idPartie: Int, idJoueur: Int, client: QuiEstCeClient){
        this.personnages = client.requeteGrilleJoueur(idPartie, idJoueur)
    }

    fun setPersonnages(nouveauxPersonnages: List<List<Personnage>>) {
        this.personnages = nouveauxPersonnages
    }

    fun getPersonnages(): List<List<Personnage>> {
        return this.personnages
    }
}
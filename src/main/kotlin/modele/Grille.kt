package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.Personnage


class Grille(
    var personnages : List<List<Personnage>> = listOf<List<Personnage>>()
) {

    fun recupererGrille(idPartie: Int, idJoueur: Int, client: QuiEstCeClient){
        this.personnages = client.requeteGrilleJoueur(idPartie, idJoueur)
    }
}
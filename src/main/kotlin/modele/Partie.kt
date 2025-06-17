package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.EtatPartie
import info.but1.sae2025.data.Joueur
import info.but1.sae2025.data.Personnage

class Partie(
    private val client: QuiEstCeClient,
    private val joueurId: Int,
    private val joueurCle: String,
    var id: Int,
) {

    var selfGrille : List<List<Personnage>>? = null
    var otherGrille : List<List<Personnage>>? = null

    var etat: EtatPartie? = null


    fun rafraichirEtat() {
        this.etat = client.requeteEtatPartie(this.id) //
        // Une fois l'état à jour, on peut en profiter pour charger les grilles des joueurs.
        chargerLesGrilles()
    }

    private fun chargerLesGrilles() {
        if (etat == null) {
            throw Error("L'état ne doit pas être null")
        }

        val nonNullEtat = etat!!

        selfGrille = client.requeteGrilleJoueur(this.id, nonNullEtat.idJoueur1)
        otherGrille = client.requeteGrilleJoueur(this.id, nonNullEtat.idJoueur2)
    }

    fun poserQuestion(idJoueur: Int, cleJoueur: String, question: String) {
        if(question.isBlank()){
            throw Error("la question est vide")
        }
        client.requetePoserQuestion(this.id, idJoueur, cleJoueur, question)
    }

    fun envoyerReponse(idJoueur: Int, cleJoueur: String, reponse: String){
        client.requeteDonnerReponse(this.id, idJoueur, cleJoueur, reponse)
    }

    fun trouve(idJoueur: Int,
               cleJoueur: String,
               ligne: Int,
               colonne: Int){
        client.requeteTrouve(this.id, idJoueur, cleJoueur, ligne, colonne)
    }

    fun rejoindrePartie(idPartie: Int,
                        idJoueur: Int,
                        cleJoueur: String
    ){
        this.etat = client.requeteRejoindrePartie(idPartie, idJoueur, cleJoueur)
        this.id = idPartie
    }

}
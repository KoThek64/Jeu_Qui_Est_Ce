package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.EtatPartie
import info.but1.sae2025.data.Joueur
import info.but1.sae2025.data.Personnage
import info.but1.sae2025.exceptions.QuiEstCeException

class Partie(
    private val client: QuiEstCeClient,
    val joueurId: Int,
    private val joueurCle: String,
    var id: Int,
) {

    var selfGrille : Grille = Grille()
    var otherGrille : Grille = Grille()

    var etat: EtatPartie? = null


    fun rafraichirEtat() {
        this.etat = client.requeteEtatPartie(this.id) //
        // Une fois l'état à jour, on peut en profiter pour charger les grilles des joueurs.
        chargerLesGrilles()
    }

    private fun chargerLesGrilles() {
        if (etat == null) throw Error("L'état ne doit pas être null")
        val nonNullEtat = etat!!

        try {
            // Grille du joueur courant
            val selfGrilleData = client.requeteGrilleJoueur(this.id, this.joueurId)
            selfGrille.setPersonnages(selfGrilleData)

            // Grille de l’adversaire si présent
            if (nonNullEtat.idJoueur2 > 0) {
                val idAdverse = if (this.joueurId == nonNullEtat.idJoueur1) {
                    nonNullEtat.idJoueur2
                } else {
                    nonNullEtat.idJoueur1
                }
                val otherGrilleData = client.requeteGrilleJoueur(this.id, idAdverse)
                otherGrille.setPersonnages(otherGrilleData)
            }

            println("Grilles chargées : ${selfGrille.getPersonnages().flatten().size} pour le joueur courant")
        } catch (e: Exception) {
            println("Erreur lors du chargement des grilles : ${e.message}")
            throw e
        }
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
    ) : Exception? {
        try {
            this.etat = client.requeteRejoindrePartie(idPartie, idJoueur, cleJoueur)
            this.id = idPartie
            return null
        } catch (e: Exception) {
            return e
        }

        /*
        this.etat = client.requeteRejoindrePartie(idPartie, idJoueur, cleJoueur)
        this.id = idPartie
         */
    }

}
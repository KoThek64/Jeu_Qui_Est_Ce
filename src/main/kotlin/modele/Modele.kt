package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur

class Modele(private val client: QuiEstCeClient) {

    // Stocke les informations du joueur principal après son inscription.
    var monJoueur: IdentificationJoueur? = null

    // Stocke la partie actuellement en cours.
    var partieEnCours: Partie? = null


    fun inscription(nom: String, prenom: String): Exception? {

        try {
            val identification = client.requeteCreationJoueur(nom, prenom)
            this.monJoueur = identification
            return null

        } catch (e: Exception) {
            return e
        }
    }


    fun creerPartie(): Boolean {

        if (this.monJoueur?.id !in client.requeteJoueurs()) {
            println("Erreur : Le joueur doit être inscrit pour créer une partie.")
            return false
        } else{
            this.partieEnCours = Partie(client, this.monJoueur!!.id, this.monJoueur!!.cle, client.requeteCreationPartie(this.monJoueur!!.id, this.monJoueur!!.cle))
            return true
        }
    }

    fun getListeParties(): List<Int> {
        return client.requeteListeParties()
    }

    fun getListePartiesCreees(): List<Int> {
        return client.requeteListePartiesCreees()
    }

    fun getListePartiesTerminees(): List<Int> {
        return client.requeteListePartiesTerminees()
    }

    fun getClient(): QuiEstCeClient {
        return client
    }

    fun getMonJoueurId(): Int? {
        return monJoueur?.id
    }

    fun getMonJoueurCle(): String? {
        return monJoueur?.cle
    }
}
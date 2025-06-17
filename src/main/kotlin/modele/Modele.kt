package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur

class Modele(private val client: QuiEstCeClient) {

    // Stocke les informations du joueur principal après son inscription.
    private var monJoueur: IdentificationJoueur? = null

    // Stocke la partie actuellement en cours.
    private var partieEnCours: Partie? = null


    fun inscription(nom: String, prenom: String): Exception? {
        // On utilise la fonction exacte du diagramme pour créer un joueur.
        try {
            val identification = client.requeteCreationJoueur(nom, prenom)
            this.monJoueur = identification
            return null

        } catch (e: Exception) {
            return e
        }
    }

    fun seConnecter0(id : Int, cle : String){
        if (id !in client.requeteJoueurs()){
            throw Error("Le joueur n'existe pas.")
        }else{
            //TODO
        }

    }


    /**
     * Crée une nouvelle partie sur le serveur.
     * La méthode `requeteCreationPartie` nécessite l'ID et la clé du joueur.
     * @return true si la création a réussi, false sinon.
     */
    fun creerPartie(): Boolean {
        val joueur = this.monJoueur
        if (joueur == null) {
            println("Erreur : Le joueur doit être inscrit pour créer une partie.")
            return false
        }

        // On appelle la création de partie avec l'id et la clé du joueur.
        // Cette fonction retourne l'ID de la nouvelle partie.
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)

        if (idPartie != null) {
            // On instancie notre objet Partie avec l'ID retourné par le serveur.
            this.partieEnCours = Partie(client, joueur.id, joueur.cle, idPartie)
            println("Partie créée avec succès. ID : $idPartie")
            this.partieEnCours?.rafraichirEtat()
            return true
        }
        println("Erreur : La création de la partie a échoué.")
        return false
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
}
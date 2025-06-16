package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur

class Modele(private val client: QuiEstCeClient) {

    // Stocke les informations du joueur principal après son inscription.
    private var monJoueur: IdentificationJoueur? = null

    // Stocke la partie actuellement en cours.
    private var partieEnCours: Partie? = null

    /**
     * Inscrit un nouveau joueur en appelant le client.
     * La méthode du client `requeteCreationJoueur` retourne un objet IdentificationJoueur.
     * @return true si l'inscription a réussi, false sinon.
     */
    fun inscription(nom: String, prenom: String): Boolean {
        // On utilise la fonction exacte du diagramme pour créer un joueur.
        val identification = client.requeteCreationJoueur(nom, prenom) //

        if (identification != null) {
            this.monJoueur = identification
            println("Inscription réussie pour ${prenom} ${nom}. ID: ${monJoueur?.id}")
            return true
        }
        println("Erreur : L'inscription a échoué.")
        return false
    }

    /**
     * Crée une nouvelle partie sur le serveur.
     * La méthode `requeteCreationPartie` nécessite l'ID et la clé du joueur.
     * @return true si la création a réussi, false sinon.
     */
    fun creerPartie(): Boolean {
        val joueur = monJoueur
        if (joueur == null) {
            println("Erreur : Le joueur doit être inscrit pour créer une partie.")
            return false
        }

        // On appelle la création de partie avec l'id et la clé du joueur.
        // Cette fonction retourne l'ID de la nouvelle partie.
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle) //

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
}
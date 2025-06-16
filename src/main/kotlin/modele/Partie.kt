package modele

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.EtatPartie
import info.but1.sae2025.data.Personnage

<<<<<<< HEAD
class Partie(private val client: QuiEstCeClient, val id: Int) {

    // L'état de la partie, qui sera rempli via la requête au client.
    var etat: EtatPartie? = null

    val grilles: List<List<List<Personnage>>> = listOf<List<List<Personnage>>>()

    /**
     * Met à jour l'état de la partie en appelant le client.
     * Utilise `requeteEtatPartie(idPartie)` qui retourne un objet `EtatPartie`.
     */

    fun rafraichirEtat() {
        this.etat = client.requeteEtatPartie(this.id) //
        // Une fois l'état à jour, on peut en profiter pour charger les grilles des joueurs.
        chargerLesGrilles()
    }

    /**
     * Pour chaque joueur présent dans la partie, charge sa grille de personnages.
     * Note: On suppose que `etat.joueurs` est disponible, comme dans votre code initial.
     */
    private fun chargerLesGrilles() {
        grilles[0] = client.requeteGrilleJoueur(this.id, this.etat.idJoueur1)
        grilles[1] = client.requeteGrilleJoueur(this.id, this.etat.idJoueur2)
=======
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
>>>>>>> dcedb1f53411b82cfa831e92744a068613066abe
    }

    // ... autres fonctions de jeu à implémenter, comme :
    fun poserQuestion(idJoueur: Int, cleJoueur: String, question: String) {
        client.requetePoserQuestion(this.id, idJoueur, cleJoueur, question)
    }
}
package modele

class Reponse(valeur : String) {
    private val valeur: String
    private val question: Question

    init {
        this.valeur = valeur
        this.question = Question()
    }

    fun getValeur() {
        println(this.valeur)
    }
}

package modele

class Question(texte : String = ""){
    private var texte : String

    init {
        this.texte = texte
    }

    fun getTexte(){
        println(this.texte)
    }
}
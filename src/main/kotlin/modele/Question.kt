package modele

class Question(texte : String){
    private var texte : String = texte

    fun getTexte(){
        println(this.texte)
    }
}
package Modele

class Question(texte : String){
    private var texte : String = texte

    fun getTexte(){
        println(this.texte)
    }
}
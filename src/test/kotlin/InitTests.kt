package test

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InitTests() {

    private var client: QuiEstCeClient = QuiEstCeClient("localhost", 8080)

    fun nom() : String{
        val str = listOf(
            "Adam", "Albert", "Ali", "Allard", "Almeida", "Alves", "André", "Antoine", "Arnaud", "Aubry",
            "Auger", "Bachelier", "Barbier", "Baron", "Barraud", "Barre", "Barreau", "Barry", "Barthez", "Bastien",
            "Baudry", "Bauer", "Baumann", "Bazin", "Beaumont", "Becquet", "Benoît", "Béranger", "Berger", "Bernard",
            "Bertrand", "Besson", "Bianchi", "Binet", "Blanchard", "Blanchet", "Blondel", "Bois", "Bonhomme", "Bonnet",
            "Borel", "Boucher", "Boulay", "Bouquet", "Bourdon", "Bourgeois", "Bousquet", "Boutin", "Brasseur", "Braun",
            "Brillant", "Brisset", "Brochard", "Brun", "Brunel", "Bruno", "Bucheron", "Buffet", "Buisson", "Cadiou",
            "Camus", "Carpentier", "Carré", "Caron", "Casanova", "Castaing", "Castro", "Cavalier", "Chalumeau", "Chambon",
            "Chapuis", "Charbonnier", "Charpentier", "Chartier", "Chauvet", "Chemin", "Chevalier", "Chevrier", "Clément", "Cloarec",
            "Colin", "Collin", "Combes", "Constant", "Cordier", "Cornet", "Cossart", "Coste", "Coulon", "Courtois",
            "Couturier", "Crozat", "Cuvier", "Da Costa", "Dalibard", "Daniel", "Danton", "Darmon", "Daumier", "David",
            "Debray", "Debruyne", "Decroix", "Delage", "Delamare", "Delattre", "Delcroix", "Delmas", "Delorme", "Delpech",
            "Denis", "Descamps", "Deschamps", "Deshayes", "Desnos", "Devaux", "Dewitte", "Dias", "Didier", "Dijoux",
            "Dion", "Dominique", "Donati", "Doucet", "Drouet", "Dubois", "Dubreuil", "Duchamp", "Duchesne", "Dufour",
            "Dumas", "Dumont", "Dupont", "Dupuis", "Durand", "Duret", "Dussault", "Duvivier", "Fabre", "Faivre",
            "Farge", "Faure", "Favre", "Fayolle", "Fernandes", "Ferrand", "Ferreira", "Fischer", "Fontaine", "Forestier",
            "Fouquet", "Fourcade", "Fournier", "François", "Fréchette", "Fremont", "Fresneau", "Frison", "Froger", "Gagnon",
            "Gallet", "Garnier", "Gaspard", "Gaubert", "Gauthier", "Gautier", "Gérard", "Gibert", "Gilbert", "Gilles",
            "Girard", "Giraud", "Godard", "Gomes", "Gonzalez", "Gosselin", "Gouin", "Granger", "Grasset", "Grégoire",
            "Grenier", "Guégan", "Guérin", "Guibert", "Guichard", "Guillaume", "Guillot", "Guy", "Guyon", "Hamon",
            "Hardy", "Henry", "Herbin", "Heredia", "Hernandez", "Heurtin", "Hilaire", "Hoarau", "Hoareau", "Hubert",
            "Humbert", "Imbert", "Issa", "Jacques", "Jacquet", "Jacquin", "Jalabert", "Jamet", "Janin", "Jean",
            "Joly", "Joseph", "Joubert", "Jourdain", "Jouve", "Julien", "Kaiser", "Keller", "Khaled", "Klein",
            "Kouassi", "Lacroix", "Lafon", "Lafont", "Lagarde", "Lagrange", "Lahaye", "Lambert", "Lamy", "Langlois"
        )
        val nom = str.random()
        return nom
    }

    fun prenom() : String{
        val str = listOf(
            "Aaron", "Abel", "Abigail", "Achille", "Adam", "Adèle", "Adrian", "Aïcha", "Aiden", "Alban",
            "Alessia", "Alex", "Alexa", "Alexandre", "Alexia", "Alix", "Aliyah", "Alma", "Amandine", "Amara",
            "Amélie", "Amine", "Amir", "Ana", "Anaëlle", "Anas", "Andréa", "Angèle", "Angelo", "Anna",
            "Annabelle", "Anouk", "Anthony", "Antonin", "Anya", "Aria", "Ariana", "Ariel", "Armand", "Arnaud",
            "Arthur", "Asha", "Asma", "Astrid", "Aubin", "Audrey", "Augustin", "Aurélie", "Auriane", "Aurore",
            "Aya", "Ayden", "Aymeric", "Baptiste", "Basma", "Bastien", "Baya", "Benjamin", "Benoît", "Bilal",
            "Brandon", "Brieuc", "Brigitte", "Camélia", "Camille", "Candice", "Capucine", "Carla", "Cassandra", "Catherine",
            "Cédric", "Céleste", "Célia", "Céline", "Chadi", "Charles", "Charlie", "Charlotte", "Chloé", "Christelle",
            "Clara", "Clarisse", "Clément", "Clémence", "Coline", "Constantin", "Coralie", "Corentin", "Cynthia", "Dahlia",
            "Damien", "Daniel", "Dany", "Daphné", "David", "Déborah", "Delphine", "Denis", "Diana", "Dimitri",
            "Dorian", "Dounia", "Dylan", "Éléa", "Éléanore", "Éléna", "Élias", "Élie", "Élodie", "Éloïse",
            "Émilie", "Émilien", "Emma", "Emmanuel", "Enora", "Enzo", "Erwan", "Esteban", "Estelle", "Ethan",
            "Eva", "Eve", "Faïza", "Fanny", "Farah", "Fatima", "Félicie", "Félix", "Flavie", "Florent",
            "Floriane", "Gabriel", "Gaël", "Gaspard", "Gauthier", "Geoffrey", "Georges", "Géraldine", "Gianni", "Gilles",
            "Gisèle", "Gladys", "Grégoire", "Guillaume", "Gustave", "Gwenaëlle", "Habib", "Hadrien", "Hakim", "Hana",
            "Hanna", "Haroun", "Hassan", "Héloïse", "Henri", "Honorine", "Hugo", "Ibrahim", "Ilana", "Inaya",
            "Inès", "Irène", "Isaac", "Isabelle", "Ishaq", "Isis", "Ismaël", "Jade", "Jamel", "Jamila",
            "Janis", "Jasmine", "Jean", "Jean-Baptiste", "Jeanne", "Jérémy", "Jessica", "Joachim", "Joan", "Joanna",
            "Jocelyn", "Joe", "Johan", "Jonathan", "Jordan", "Joséphine", "Jules", "Julia", "Julian", "Julien",
            "Julie", "Juliette", "Justine", "Kader", "Kaïs", "Karim", "Karine", "Kassandra", "Katia", "Kawthar",
            "Kelly", "Kenza", "Kévin", "Khadija", "Khaled", "Kim", "Laetitia", "Lamia", "Lana", "Laura",
            "Laure", "Laurence", "Laurent", "Léna", "Léa", "Léandre", "Léo", "Léon", "Léonie", "Lilian",
            "Lina", "Line", "Lisa", "Loïc", "Lola", "Lorena", "Loris", "Lou", "Louis", "Louise"
        )
        val prenom = str.random()
        return prenom
    }

    fun nomsApostrophe() : String{
        val nomsAvecApostrophes = listOf(
            "O'Connor", "O'Neill", "O'Brien", "O'Malley", "O'Donnell", "O'Reilly", "D'Orazio", "D'Angelo",
            "D'Almeida", "D'Arcy", "D'Artagnan", "L'Olivier", "L'Écuyer", "L'Hérault", "L'Archevêque", "D'Aubigné", "D'Hervé", "D'Haese", "D'Este"
        )
        val result = nomsAvecApostrophes.random()
        return result
    }

    fun prenomsApostrophe() : String{
        val prenomsAvecApostrophes = listOf(
            "O'Berry", "O'Brien", "O'Brown", "O'Callaghan", "O'Callahan", "O'Connor", "O'Conner", "O'Cookie",
            "O'Dell", "O'Donnell", "O'Dorsett", "O'Driscoll", "O'Dwyer", "O'Fayard", "O'Neill", "O'Neal", "O'Neill",
            "O'Plessis", "O'Sullivan", "O'Sullivan", "O'Brien", "O'Brien", "O'Brien", "O'Brien", "O'Brien", "O'Brien",
        )
        val result = prenomsAvecApostrophes.random()
        return result
    }

    fun prenomsCompose() : String{
        val prenomsComposes = listOf(
            "Jean-Luc", "Marie-Lise", "Anne-Sophie", "Jean-Baptiste", "Marie-Ange", "Anne-Laure", "Jean-Michel",
            "Marc-Antoine", "Chloé-Rose", "Léa-Marie", "Sarah-Jane", "Emma-Lou", "Léo-Paul", "Paul-Henri", "Claire-Lise",
            "Marie-Noëlle", "Jean-Noël", "Anna-Léna", "Lou-Ann", "Lily-Rose", "Elsa-Mae", "Tom-Eliott", "Noé-Lys", "Nina-Sarah", "Zoé-Claire"
        )
        val result = prenomsComposes.random()
        return result
    }

    fun nomsCompose() : String{
        val nomsComposes = listOf(
            "Durand-Lemoine", "Martin-Dupuis", "Petit-Jean", "Morel-Laurent", "Garcin-Boulay",
            "Lemoine-Bernard", "Benoît-Henry", "Blanc-Dumont", "Charpentier-Leclerc", "Girard-Morel", "Fontaine-Roche", "Legrand-Bazin",
            "Chevalier-Dubois", "Robert-Garnier", "Noël-Fabre", "David-Lambert", "Faure-Collin",
            "Bertrand-Masson", "Chapelain-Roux", "Renard-Breton", "Lemoine-Valentin", "Gaudin-Rousseau", "Vidal-Denis", "Giraud-Petit", "Bonnet-Laporte"
        )
        val result = nomsComposes.random()
        return result
    }

    fun id() : Int{
        val listeId = client.requeteJoueurs()
        val random = listeId.random()
        return random
    }


}
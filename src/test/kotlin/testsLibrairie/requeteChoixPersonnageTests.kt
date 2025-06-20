package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull

class requeteChoixPersonnageTests {

    private lateinit var client: QuiEstCeClient
    private val initTests = InitTests()

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE CHOIC PERSONNAGE-----------//
    //Test non fonctionnel
    //@Test
    fun requeteChoixPersonnageValide() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        val personnage = client.requeteChoixPersonnage(idPartie, joueur.id, joueur.cle, 1, 1)
        assertNotNull(personnage)
    }

    @Test
    fun requeteChoixPersonnageIdPartieInvalide() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<QuiEstCeException> {
            client.requeteChoixPersonnage(initTests.idPartie(), joueur.id, joueur.cle, 1, 1)
        }
    }

    @Test
    fun requeteChoixPersonnageIdJoueurInvalide() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        assertThrows<QuiEstCeException> {
            client.requeteChoixPersonnage(idPartie, initTests.idJoueurs(), joueur.cle, 1, 1)
        }
    }

    @Test
    fun requeteChoixPersonnageIdJoueurIncorrecte() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        assertThrows<IllegalArgumentException> {
            client.requeteChoixPersonnage(idPartie, joueur.id, "Incorrecte", 1, 1)
        }
    }

    @Test
    fun requeteChoixPersonnageLigneIncorrecte() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        assertThrows<IllegalArgumentException> {
            client.requeteChoixPersonnage(idPartie, joueur.id, joueur.cle, 1000000000, 1)
        }
    }

    @Test
    fun requeteChoixPersonnageColonneIncorrecte() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        assertThrows<IllegalArgumentException> {
            client.requeteChoixPersonnage(idPartie, joueur.id, joueur.cle, 1, 1000000000)
        }
    }
}
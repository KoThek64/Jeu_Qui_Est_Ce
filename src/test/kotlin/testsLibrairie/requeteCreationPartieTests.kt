package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class requeteCreationPartieTests {
    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE DE CREATION DE PARTIE-----------//
    @Test
    fun requeteCreationPartieValide() {
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenom())
        val id = client.requeteCreationPartie(joueur.id, joueur.cle)
        assertNotNull(id)
        assertTrue(id > 0)
    }

    @Test
    fun requeteCreationPartieCleJoueurVide() {
        assertThrows<IllegalArgumentException> {
            client.requeteCreationPartie(InitTests().idJoueurs(), "")
        }
    }

    @Test
    fun requeteCreationPartieCleJoueurIncorrecte() {
        assertThrows<IllegalArgumentException> {
            client.requeteCreationPartie(InitTests().idJoueurs(), "CleIncorrecte")
        }
    }

    @Test
    fun requeteCreationPartieIdJoueurInvalide(){
        val n1 = Random.nextInt(1,999999999)
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenom())
        assertThrows<QuiEstCeException> {
            client.requeteCreationPartie(n1, joueur.cle)
        }
    }

    @Test
    fun requeteCreationPartieIdJoueurTropGrand(){
        val n = Random.nextInt(10000000000.toInt(), Int.MAX_VALUE)
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenom())
        assertThrows<QuiEstCeException> {
            client.requeteCreationPartie(n, joueur.cle)
        }
    }
}

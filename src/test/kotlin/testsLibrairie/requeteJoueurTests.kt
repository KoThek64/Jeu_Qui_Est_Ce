package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class requeteJoueurTests {
    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE DE RECUPERATION DU JOUEUR-----------//
    @Test
    fun requeteJoueurValide() {
        val joueur = client.requeteJoueur(InitTests().idJoueurs())
        assertNotNull(client.requeteJoueurs())
        assertTrue(joueur.nom.isNotEmpty())
        assertTrue(joueur.prenom.isNotEmpty())
    }

    @Test
    fun requeteJoueurIdInvalide0() {
        assertThrows<QuiEstCeException> {
            client.requeteJoueur(0)
        }
    }

    @Test
    fun requeteJoueurIdValideTropPetit() {
        val n = Random.nextInt(1,999999999)
        assertThrows<QuiEstCeException> {
            client.requeteJoueur(n)
        }
    }

    @Test
    fun requeteJoueurIdInvalideTropGrand() {
        val n = Random.nextInt(10000000000.toInt(), Int.MAX_VALUE)
        assertThrows<QuiEstCeException> {
            client.requeteJoueur(n)
        }
    }

    @Test
    fun requeteJoueurIdInvalideNegatif() {
        val n = -Random.nextInt(1, Int.MAX_VALUE)
        assertThrows<IllegalArgumentException> {
            client.requeteJoueur(n)
        }
    }

}

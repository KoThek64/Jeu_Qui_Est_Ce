package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class requeteEtatPartieTests {
    private lateinit var client: QuiEstCeClient
    private val initTests = InitTests()

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE DE RECUPERATION DE L'ETAT DE LA PARTIE-----------//
    @Test
    fun requeteEtatPartieValide() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        val etat = client.requeteEtatPartie(idPartie)
        assertNotNull(etat)
    }

    @Test
    fun requeteEtatPartieIdInvalideIdJoueur() {
        assertThrows<QuiEstCeException> {
            client.requeteEtatPartie(initTests.idJoueurs())
        }
    }

    @Test
    fun requeteEtatPartieInvalideIdPartieTropGrand() {
        val n = Random.nextInt(10000000000.toInt(), Int.MAX_VALUE)
        assertThrows<QuiEstCeException> {
            client.requeteEtatPartie(n)
        }
    }

    @Test
    fun requeteEtatPartieInvalideIdPartieTropPetit() {
        val n = Random.nextInt(1,999999999)
        assertThrows<QuiEstCeException> {
            client.requeteEtatPartie(n)
        }
    }

    @Test
    fun requeteEtatPartieInvalideIdPartieNegatif() {
        val n = -Random.nextInt(1, Int.MAX_VALUE)
        assertThrows<IllegalArgumentException> {
            client.requeteEtatPartie(n)
        }
    }
}
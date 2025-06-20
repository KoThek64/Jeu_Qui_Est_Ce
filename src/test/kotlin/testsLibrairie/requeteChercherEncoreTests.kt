package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random
import kotlin.test.assertNotNull

class requeteChercherEncoreTests {

    private lateinit var client: QuiEstCeClient
    private val initTests = InitTests()

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE CHERCHE ENCORE-----------//
    @Test
    fun requeteChercheEncoreInvalide() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<QuiEstCeException> {
            client.requeteChercherEncore(initTests.idPartie(), joueur1.id, joueur1.cle)
        }
    }

    @Test
    fun requeteChercheEncoreInvalideIdPartieNegatif() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val n = -Random.nextInt(1, Int.MAX_VALUE)
        assertThrows<IllegalArgumentException> {
            client.requeteChercherEncore(n, joueur1.id, joueur1.cle)
        }
    }

    @Test
    fun requeteChercheEncoreInvalideIdJoueurNegatif() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val n = -Random.nextInt(1, Int.MAX_VALUE)
        assertThrows<IllegalArgumentException> {
            client.requeteChercherEncore(initTests.idPartie(), n, joueur1.cle)
        }
    }

    @Test
    fun requeteChercheEncoreInvalideCleJoueurIncorrecte() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<IllegalArgumentException> {
            client.requeteChercherEncore(initTests.idPartie(), joueur1.id, "Incorrecte")
        }
    }
}
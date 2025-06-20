package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random

class requeteRejoindrePartieTests {

    private lateinit var client: QuiEstCeClient
    private val initTests = InitTests()

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE REJOINDRE UNE PARTIE-----------//
    @Test
    fun requeteRejoindrePartie() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val etat = client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
        assertNotNull(etat)
    }

    @Test
    fun requeteRejoindrePartieIdInvalide() {
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<QuiEstCeException>{
            client.requeteRejoindrePartie(0, joueur2.id, joueur2.cle)
        }
    }

    @Test
    fun requeteRejoindrePartieIdInvalideTropPetit() {
        val n = Random.nextInt(1,999999999)
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<QuiEstCeException>{
            client.requeteRejoindrePartie(n, joueur2.id, joueur2.cle)
        }
    }

    @Test
    fun requeteRejoindrePartieIdInvalideTropGrand() {
        val n = Random.nextInt(10000000000.toInt(), Int.MAX_VALUE)
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<QuiEstCeException>{
            client.requeteRejoindrePartie(n, joueur2.id, joueur2.cle)
        }
    }

    @Test
    fun requeteRejoindrePartieIdInvalideNegatif() {
        val n = -Random.nextInt(1, Int.MAX_VALUE)
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<IllegalArgumentException>{
            client.requeteRejoindrePartie(n, joueur2.id, joueur2.cle)
        }
    }

}
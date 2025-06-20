package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class requeteCreationPlayerTests() {
    private lateinit var client: QuiEstCeClient
    private val initTests = InitTests()

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }


    //-----------TESTS DE LA REQUETE DE CREATION D'UN JOUEUR-----------//
    @Test
    fun requeteCreationJoueurValideNonNull() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValideNomComposé() {
        val joueur = client.requeteCreationJoueur(initTests.nomsCompose(), initTests.prenom())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValidePrenomComposé() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenomsCompose())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValideCaracteresSpeciaux1() {
        val joueur = client.requeteCreationJoueur(initTests.nomsApostrophe(), initTests.prenom())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValideCaracteresSpeciaux2() {
        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenomsApostrophe())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurNomNull() {
        assertThrows<IllegalArgumentException> {
            client.requeteCreationJoueur("", initTests.prenom())
        }
    }

    @Test
    fun requeteCreationJoueurPrenomNull() {
        assertThrows<IllegalArgumentException> {
            client.requeteCreationJoueur(initTests.nom(), "")
        }
    }

}
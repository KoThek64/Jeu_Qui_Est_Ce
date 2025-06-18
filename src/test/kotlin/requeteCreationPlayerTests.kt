package test

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class requeteCreationPlayerTests() {
    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }


    //-----------TESTS DE LA REQUETE DE CREATION D'UN JOUEUR-----------//
    @Test
    fun requeteCreationJoueurValideNonNull() {
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenom())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValideNomComposé() {
        val joueur = client.requeteCreationJoueur(InitTests().nomsCompose(), InitTests().prenom())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValidePrenomComposé() {
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenomsCompose())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValideCaracteresSpeciaux1() {
        val joueur = client.requeteCreationJoueur(InitTests().nomsApostrophe(), InitTests().prenom())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurValideCaracteresSpeciaux2() {
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenomsApostrophe())
        assertNotNull(joueur)
        assertTrue(joueur.id > 0)
        assertNotNull(joueur.cle)
    }

    @Test
    fun requeteCreationJoueurNomNull() {
        assertThrows<IllegalArgumentException> {
            client.requeteCreationJoueur("", InitTests().prenom())
        }
    }

    @Test
    fun requeteCreationJoueurPrenomNull() {
        assertThrows<IllegalArgumentException> {
            client.requeteCreationJoueur(InitTests().nom(), "")
        }
    }

}
package test

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class requeteJoueursTests {
    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE DE RECUPERATION DES JOUEURS-----------//
    @Test
    fun requeteJoueursValide() {
        val joueurs = client.requeteJoueurs()
        assertNotNull(joueurs)
        assertTrue(joueurs.isNotEmpty())
        println(joueurs)
    }

    @Test
    fun requeteJoueursVide() {
        val joueurs = listOf<Int>()
        assertNotNull(joueurs)
        assertTrue(joueurs.isEmpty())
        println(joueurs)
    }

}
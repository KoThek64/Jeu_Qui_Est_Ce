package test

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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
}

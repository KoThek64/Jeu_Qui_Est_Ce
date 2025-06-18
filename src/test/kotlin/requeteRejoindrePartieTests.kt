package test

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class requeteRejoindrePartieTests {

    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE REJOINDRE UNE PARTIE-----------//
    @Test
    fun requeteRejoindrePartieValide() {
        val joueur = client.requeteCreationJoueur(InitTests().nom(), InitTests().prenom())
        val id = InitTests().idPartie()
        val reponse = client.requeteRejoindrePartie(id, joueur.id, joueur.cle)
        assertNotNull(reponse)
        assertTrue(reponse.idJoueur1 == joueur.id)
    }
}
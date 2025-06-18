package test

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class requeteEtatPartieTests {
    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    //-----------TESTS DE LA REQUETE DE RECUPERATION DE L'ETAT DE LA PARTIE-----------//
    @Test
    fun requeteEtatPartieValide() {
        val etat = client.requeteEtatPartie(InitTests().idJoueurs())
        //TODO("à finir...")
    }
}
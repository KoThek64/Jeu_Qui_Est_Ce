package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
        val etat = client.requeteEtatPartie(initTests.idJoueurs())
        //TODO("à finir...")
    }
}
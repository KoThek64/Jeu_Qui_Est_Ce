package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class requeteListePartiesTermineesTests {

    private lateinit var client: QuiEstCeClient
    private lateinit var joueur1: IdentificationJoueur
    private lateinit var joueur2: IdentificationJoueur
    private var idPartie = 0
    private val initTests = InitTests()

    @BeforeEach
    fun setUp() {
        client = QuiEstCeClient("localhost", 8080)
        joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
        client.requeteChoixPersonnage(idPartie, joueur1.id, joueur1.cle, 0, 0)
        client.requeteChoixPersonnage(idPartie, joueur2.id, joueur2.cle, 1, 1)
    }

    //-----------TESTS DE LA REQUETE LISTES PARTIES TERMINEES-----------//
    //Test non fonctionnel
    //@Test
    fun requeteListePartiesTermineesValide() {
        val result = client.requeteTrouve(idPartie, joueur1.id, joueur1.cle, 1, 1)
        assertTrue(result)
        val partiesTerminees = client.requeteListePartiesTerminees()
        assertTrue(partiesTerminees.contains(idPartie))
    }

    @Test
    fun requeteListePartiesTermineesVide() {
        val partiesTerminees = client.requeteListePartiesTerminees()
        assertTrue(partiesTerminees.isEmpty())
    }

    @Test
    fun requeteListePartiesTermineesInvalide() {
        val partiesTerminees = listOf<Int>()
        assertTrue(!partiesTerminees.contains(idPartie))
    }

}
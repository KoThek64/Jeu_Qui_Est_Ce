package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class requeteListePartiesCreeesTests {
    /**
     * RELANCER LE SERVEUR LOCAL AVANT DE LANCER CES TESTS !
     */

    private lateinit var client: QuiEstCeClient
    private lateinit var initTests: InitTests

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
        initTests = InitTests()
    }

    @Test
    fun requeteListePartiesCreeesAucunePartieEnAttente() {

        val partiesCreees = client.requeteListePartiesCreees()
        assertNotNull(partiesCreees, "La list ne devrait pas être null !")
        assertTrue(partiesCreees.isEmpty(), "La list devrait être vide !: $partiesCreees")
    }

    @Test
    fun requeteListePartiesCreeesUnePartieEnAttente() {

        val joueur = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur.id, joueur.cle)
        val partiesCreees = client.requeteListePartiesCreees()
        assertNotNull(partiesCreees, "La list ne devrait pas être null !")
        assertTrue(partiesCreees.contains(idPartie), "La list devrait contenir l'id de la partie créée: $idPartie")
    }

    @Test
    fun requeteListePartiesCreeesPlusieursPartiesEnAttente() {

        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie1 = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie2 = client.requeteCreationPartie(joueur2.id, joueur2.cle)
        val joueur3 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie3 = client.requeteCreationPartie(joueur3.id, joueur3.cle)
        val partiesCreees = client.requeteListePartiesCreees()
        assertNotNull(partiesCreees, "La list ne devrait pas être null ")
        assertTrue(partiesCreees.containsAll(listOf(idPartie1, idPartie2, idPartie3)),
            "La list devrait contenir l'ids des parties créées: $idPartie1, $idPartie2, $idPartie3")
    }

    /*
    @Test
    fun requeteListePartiesCreeesServeurInjoignable() {

        val invalideClient = QuiEstCeClient("invalid_host", 8080)
        assertThrows<ConnectException> {
            invalideClient.requeteListePartiesCreees()
        }
    }
    */


}
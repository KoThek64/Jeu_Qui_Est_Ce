package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.exceptions.QuiEstCeException
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class requeteTrouveTests {

    private lateinit var client: QuiEstCeClient
    private val initTests = InitTests()
    lateinit var joueur1: IdentificationJoueur
    lateinit var joueur2: IdentificationJoueur
    var idPartie = 0

    @BeforeEach
    fun setUp() {
        client = QuiEstCeClient("localhost", 8080)
        joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
    }

    //-----------TESTS DE LA REQUETE TROUVE UNE PARTIE-----------//
    //Test non fonctionnel
    //@Test
    fun requeteTrouveValideBonneCase() {
        client.requeteRejoindrePartie(idPartie, joueur1.id, joueur1.cle)
        client.requeteGrilleJoueur(idPartie, joueur1.id)
        client.requeteGrilleJoueur(idPartie, joueur2.id)
        client.requeteChoixPersonnage(idPartie, joueur1.id, joueur1.cle, 1, 1)
        client.requeteChoixPersonnage(idPartie, joueur2.id, joueur2.cle, 2, 2)
        client.requetePoserQuestion(idPartie, joueur1.id, joueur1.cle, "QUESTION")
        client.requeteDonnerReponse(idPartie, joueur2.id, joueur2.cle, "OUI")
        assert(!client.requeteTrouve(idPartie, joueur1.id, joueur1.cle, 1, 1))
    }

    @Test
    fun requeteTrouveInvalideMauvaiseCase() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        assertThrows<QuiEstCeException> {
            val result = client.requeteTrouve(idPartie, joueur1.id, joueur1.cle, 2, 2)
        }
    }

    @Test
    fun requeteTrouveInvalideLigneHorsGrille() {
        assertThrows<IllegalArgumentException> {
            client.requeteTrouve(idPartie, joueur1.id, joueur1.cle, -1, 2)
        }
    }

    @Test
    fun requeteTrouveInvalideColonneHorsGrille() {
        assertThrows<IllegalArgumentException> {
            client.requeteTrouve(idPartie, joueur1.id, joueur1.cle, 1, 6)
        }
    }

    @Test
    fun requeteTrouveInvalideIdPartie() {
        val idFaux = 99999
        assertThrows<QuiEstCeException> {
            client.requeteTrouve(idFaux, joueur1.id, joueur1.cle, 1, 1)
        }
    }

    @Test
    fun requeteTrouveInvalideCle() {
        assertThrows<IllegalArgumentException> {
            client.requeteTrouve(idPartie, joueur1.id, "cleInvalide", 1, 1)
        }
    }
}
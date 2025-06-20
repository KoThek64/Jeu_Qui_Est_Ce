package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class requeteDonnerReponseTests {

    private lateinit var client: QuiEstCeClient
    private lateinit var initTests: InitTests

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
        initTests = InitTests()
    }

    @Test
    fun requeteDonnerReponseCleJoueurInvalide() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        assertThrows<IllegalArgumentException> {
            client.requeteDonnerReponse(idPartie, joueur1.id, "k".repeat(31), "oui")
        }
    }

    @Test
    fun requeteDonnerReponseIdJoueurInvalide() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        assertThrows<IllegalArgumentException> {
            client.requeteDonnerReponse(idPartie, -1, joueur1.cle, "oui")
        }
    }

    @Test
    fun requeteDonnerReponseIdPartieInvalide() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<IllegalArgumentException> {
            client.requeteDonnerReponse(-1, joueur1.id, joueur1.cle, "oui")
        }
    }

    @Test
    fun requeteDonnerReponseReponseInvalide() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        assertThrows<IllegalArgumentException> {
            client.requeteDonnerReponse(idPartie, joueur1.id, joueur1.cle, "truc")
        }
    }

    @Test
    fun requeteDonnerReponseValide() {
        val joueur1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val joueur2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(joueur1.id, joueur1.cle)
        client.requeteRejoindrePartie(idPartie, joueur2.id, joueur2.cle)
        client.requeteGrilleJoueur(idPartie, joueur1.id)
        client.requeteGrilleJoueur(idPartie, joueur2.id)
        client.requeteChoixPersonnage(idPartie, joueur1.id, joueur1.cle, 1, 1)
        client.requeteChoixPersonnage(idPartie, joueur2.id, joueur2.cle, 2, 2)
        client.requetePoserQuestion(idPartie, joueur1.id, joueur1.cle, "Est-ce un homme ?")
        assertNotNull(client.requeteDonnerReponse(idPartie, joueur2.id, joueur2.cle, "oui"))
    }
}
package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class requetePoserQuestionTests {

    private lateinit var client: QuiEstCeClient
    private lateinit var initTests: InitTests

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
        initTests = InitTests()
    }

    @Test
    fun requetePoserQuestionCleJouueurInvalide() {
        val j1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(j1.id, j1.cle)
        assertThrows<IllegalArgumentException> {
            client.requetePoserQuestion(idPartie, j1.id, "k".repeat(31), "Est-ce un homme ?")
        }
    }

    @Test
    fun requetePoserQuestionIdJoueurInvalide() {
        val j1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(j1.id, j1.cle)
        assertThrows<IllegalArgumentException> {
            client.requetePoserQuestion(idPartie, -1, j1.cle, "Est-ce une femme ?")
        }
    }

    @Test
    fun requetePoserQuestionIdPartieInvalide() {
        val j1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        assertThrows<IllegalArgumentException> {
            client.requetePoserQuestion(-1, j1.id, j1.cle, "question")
        }
    }

    @Test
    fun requetePoserQuestionEtatPartieInvalide() {
        val j1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(j1.id, j1.cle)
        assertThrows<QuiEstCeException> {
            client.requetePoserQuestion(idPartie, j1.id, j1.cle, "question")
        }
    }

    @Test
    fun requetePoserQuestionValide() {
        val j1 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val j2 = client.requeteCreationJoueur(initTests.nom(), initTests.prenom())
        val idPartie = client.requeteCreationPartie(j1.id, j1.cle)
        client.requeteRejoindrePartie(idPartie, j2.id, j2.cle)
        client.requeteGrilleJoueur(idPartie, j1.id)
        client.requeteGrilleJoueur(idPartie, j2.id)
        client.requeteChoixPersonnage(idPartie, j1.id, j1.cle, 1, 1)
        client.requeteChoixPersonnage(idPartie, j2.id, j2.cle, 2, 2)
        assertNotNull(client.requetePoserQuestion(idPartie, j1.id, j1.cle, "question"))
    }

}
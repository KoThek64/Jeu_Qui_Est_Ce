package test.testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import info.but1.sae2025.data.IdentificationJoueur
import info.but1.sae2025.exceptions.QuiEstCeException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random
import kotlin.test.assertNotNull

class requeteGrilleJoueurTests {

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
        client.requeteChoixPersonnage(idPartie, joueur1.id, joueur1.cle, 1, 1)
        client.requeteChoixPersonnage(idPartie, joueur2.id, joueur2.cle, 2, 3)
    }

    //-----------TESTS DE LA REQUETE GRILLE JOUEURS-----------//
    @Test
    fun requeteGrilleJoueurValideJoueur1(){
        val grille = client.requeteGrilleJoueur(idPartie, joueur1.id)
        assertEquals(4, grille.size) // 4 lignes
        grille.forEach { ligne -> assertEquals(6, ligne.size) } // 6 colonnes
    }

    @Test
    fun requeteGrilleJoueurValideJoueur2(){
        val grille = client.requeteGrilleJoueur(idPartie, joueur2.id)
        assertNotNull(grille)
        assertEquals(4, grille.size)
        assertTrue(grille.all { it.size == 6 })
    }

    @Test
    fun requeteGrilleJoueurInvalideIdPartieNegatif(){
        val n = -Random.nextInt(1,Int.MAX_VALUE)
        assertThrows<IllegalArgumentException> {
            client.requeteGrilleJoueur(n, joueur1.id)
        }
    }

    @Test
    fun requeteGrilleJoueurInvalideIdPartieTropGrand(){
        val n = Random.nextInt(10000000000.toInt(), Int.MAX_VALUE)
        assertThrows<QuiEstCeException> {
            client.requeteGrilleJoueur(n, joueur1.id)
        }
    }

    @Test
    fun requeteGrilleJoueurInvalideIdJoueurNegatif(){
        val n = -Random.nextInt(1,Int.MAX_VALUE)
        assertThrows<IllegalArgumentException> {
            client.requeteGrilleJoueur(idPartie, n)
        }
    }

    @Test
    fun requeteGrilleJoueurInvalideIdJoueurTropGrand(){
        val n = Random.nextInt(10000000000.toInt(), Int.MAX_VALUE)
        assertThrows<QuiEstCeException> {
            client.requeteGrilleJoueur(idPartie, n)
        }
    }

    @Test
    fun requeteGrilleJoueurInvalideIdJoueurTropPetit(){
        val n = Random.nextInt(1,999999999)
        assertThrows<QuiEstCeException> {
            client.requeteGrilleJoueur(idPartie, n)
        }
    }

}
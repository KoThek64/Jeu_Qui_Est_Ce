package modele

import info.but1.sae2025.QuiEstCeClient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ModeleTest {

    private lateinit var client: QuiEstCeClient
    private lateinit var modele: Modele

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
        modele = Modele(client)
    }

    @Test
    fun inscriptionValide() {

        val res = modele.inscription("Dupont", "José")

        assertNull(res, "Inscription devrait renvoyer null pour une inscription réussie !")
        assertNotNull(modele.monJoueur, "monJoueur devrait être initialisé !")
        assertTrue(modele.monJoueur?.id != null && modele.monJoueur?.cle != null,
            "monJoueur devrait avoir un id et une cle !")
    }

    // TODO inscriptionInvalide

    @Test
    fun creerPartieValide() {

        val inscriptionRes = modele.inscription("Dupont", "Bertrand")
        assertNull(inscriptionRes, "Inscription ne devrait pas echouer !")

        val res = modele.creerPartie()

        assertTrue(res, "creerPartie devrait renvoyer true !")
        assertNotNull(modele.partieEnCours, "partieEnCours devrait être initialisée !")
        assertTrue(modele.partieEnCours?.id != null, "partieEnCours devrait avoir un id valide !")
    }

    @Test
    fun creerPartieJoueurInexistant() {

        val result = modele.creerPartie()

        assertFalse(result, "creerPartie devrait renvoyer false si le joueur n'existe pas !")
        assertNull(modele.partieEnCours, "partieEnCours devrait rester null !")
    }

}
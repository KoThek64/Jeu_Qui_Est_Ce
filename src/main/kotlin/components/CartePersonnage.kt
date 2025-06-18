package components

import info.but1.sae2025.data.Personnage
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import javafx.scene.control.Label

class CartePersonnage(val personnage: Personnage, col: Int, line: Int) : VBox(10.0) {

    val baseUrl: String = "http://localhost:8080/resources/but1/"
    val prenom: String = personnage.prenom
    val nom: String = personnage.nom
    var urlComplet = "$baseUrl${nom.uppercase()}_${prenom.capitalize()}.jpg"
    private val imageView: ImageView
    private val nameLabel: Label

    init {
        // Configuration du conteneur VBox
        this.alignment = Pos.CENTER
        this.style = """
            -fx-background-color: white;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
        """.trimIndent()

        // Configuration de l'image
        imageView = ImageView().apply {
            try {
                image = Image(urlComplet)
                fitWidth = 100.0
                fitHeight = 100.0
                isPreserveRatio = true
            } catch (e: Exception) {
                // En cas d'erreur de chargement de l'image, on peut mettre une image par défaut
                println("Erreur de chargement de l'image: $urlComplet")
            }
        }

        // Configuration du label avec le nom
        nameLabel = Label("$prenom $nom").apply {
            style = """
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-alignment: center;
            """.trimIndent()
        }

        // Ajout des éléments au VBox
        children.addAll(imageView, nameLabel)

        // Ajout des événements de survol
        setOnMouseEntered {
            style += "-fx-background-color: #f0f0f0;"
        }

        setOnMouseExited {
            style = style.replace("-fx-background-color: #f0f0f0;", "-fx-background-color: white;")
        }

        setOnMouseClicked {
            style += """
                -fx-border-color: #4BD7D7;
                -fx-border-width: 3;
            """.trimIndent()
        }
    }

    /**
     * Réinitialise le style de la carte au style par défaut
     */
    fun resetStyle() {
        style = """
            -fx-background-color: white;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
        """.trimIndent()
    }

    /**
     * Applique le style de sélection à la carte
     */
    fun setSelectedStyle() {
        style = """
            -fx-background-color: #f0f0f0;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-border-color: #4BD7D7;
            -fx-border-width: 3;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
        """.trimIndent()
    }
}
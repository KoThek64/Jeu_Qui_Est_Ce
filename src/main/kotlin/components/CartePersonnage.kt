package components

import info.but1.sae2025.data.Personnage
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import javafx.scene.control.Label
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class CartePersonnage(val personnage: Personnage, col: Int, line: Int) : VBox(10.0) {

    val baseUrl: String = "http://172.26.69.145:8080/resources/but1/"
    val prenom: String = personnage.prenom
    val nom: String = personnage.nom
    var urlComplet = "$baseUrl${nom.uppercase()}_${prenom.capitalize()}.jpg"
    private val imageView: ImageView
    private val nameLabel: Label



    init {
        this.alignment = Pos.CENTER
        this.style = """
            -fx-background-color: white;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
        """.trimIndent()

        imageView = ImageView().apply {
            try {
                val preNom = "${nom.uppercase()}_${prenom.capitalize()}.jpg"
                val urlPerso = URLEncoder.encode(preNom, StandardCharsets.UTF_8.toString())
                urlComplet = "$baseUrl${urlPerso}"
                image = Image(urlComplet)
                fitWidth = 100.0
                fitHeight = 100.0
                isPreserveRatio = true
            } catch (e: Exception) {
                // En cas d'erreur de chargement de l'image, on peut mettre une image par défaut
                println("Erreur de chargement de l'image: $urlComplet")
            }
        }

        nameLabel = Label("$prenom $nom").apply {
            style = """
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-alignment: center;
            """.trimIndent()
        }

        children.addAll(imageView, nameLabel)

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

    fun resetStyle() {
        style = """
            -fx-background-color: white;
            -fx-padding: 10;
            -fx-background-radius: 10;
            -fx-border-radius: 10;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 0);
        """.trimIndent()
    }

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
package testsLibrairie

import info.but1.sae2025.QuiEstCeClient
import io.ktor.client.network.sockets.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.net.ConnectException
import java.nio.channels.UnresolvedAddressException

class QuiEstCeClientTests {

    private lateinit var client: QuiEstCeClient

    @BeforeEach
    fun setup() {
        client = QuiEstCeClient("localhost", 8080)
    }

    @Test
    fun QuiEstCeClientHoteInvalide() {
        assertThrows<UnresolvedAddressException> {
            QuiEstCeClient("invalide", 8080)
        }
    }

    @Test
    fun QuiEstCeClientIPInconnue() {
        assertThrows<ConnectTimeoutException> {
            QuiEstCeClient("172.26.69.15", 8080)
        }
    }

    @Test
    fun QuiEstCeClientPortDejaUtilise() {
        assertThrows<ConnectException> {
            QuiEstCeClient("172.26.69.145", 47)
        }
    }

    @Test
    fun QuiEstCeClientValide() {
        assertNotNull(client)
    }
}
package br.com.woke.infrastructure

import br.com.woke.crosscutting.utils.readJsonResource
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.http.HttpStatus

object MockServerBuilder {

    private const val port = 8091
    private val server = WireMockServer(port)
    fun getUrl() = "http://127.0.0.1:$port"

    fun start() {
        server.start()
    }

    fun withSample(): MockServerBuilder {
        server.stubFor(
            WireMock.post(WireMock.urlPathMatching("/external"))
                .willReturn(
                    WireMock.aResponse()
                        .withBody(readJsonResource("/external/valid_sample_response"))
                        .withStatus(HttpStatus.OK.value())
                )
        )
        return this
    }
}

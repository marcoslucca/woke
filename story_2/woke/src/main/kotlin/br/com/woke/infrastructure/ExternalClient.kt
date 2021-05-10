package br.com.woke.infrastructure

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    value = "ExternalClient",
    url = "\${external.client.base-url}"
)
abstract class ExternalClient {

    init {
        MockServerBuilder.apply {
            start()
            withSample()
        }
    }

    @PostMapping(path = ["/external"])
    abstract fun send(
        @RequestBody body: ExternalClientRequest,
        @RequestHeader("Accept") accept: String = "application/json"
    ): Any
}

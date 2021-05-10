package br.com.woke

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebApplication

@SuppressWarnings("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}

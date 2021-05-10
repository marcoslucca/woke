package br.com.woke.crosscutting.extension

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test


internal class MD5Test {

    @Test
    fun `should parse String to MD5 successfully`() {
        "25d55ad283aa400af464c76d713c07ad" shouldBeEqualTo "12345678".toMD5()
    }
}

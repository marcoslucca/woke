package br.com.woke.crosscutting.extension

import arrow.core.Option
import java.util.Optional

fun <T> Optional<T>.toOption() = Option.fromNullable(this.orElse(null))

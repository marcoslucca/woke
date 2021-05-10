package br.com.woke.crosscutting.utils

fun readJsonResource(fileName: String) = ClassLoader.getSystemResource("json/$fileName.json").readText()

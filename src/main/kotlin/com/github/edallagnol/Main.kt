package com.github.edallagnol

fun main() {
    // #TODO criar parâmetro (verificar encoding gravação)
    val contaDefault = null
    InterMobillsConverter(contaDefault).convert(System.`in`, System.out);
}
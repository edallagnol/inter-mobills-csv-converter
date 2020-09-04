package com.github.edallagnol

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

class InterMobillsConverterTest {
    @Test
    fun convertTest() {
        val out = ByteArrayOutputStream()
        InterMobillsConverter("contaTeste")
                .convert(javaClass.classLoader.getResourceAsStream("ExtratoInter.csv")!!, out);
        val csvMobills = out.toString();
        val expectedCsv = javaClass.classLoader.getResourceAsStream("Mobills.csv")!!
                .bufferedReader().readText();
        assertEquals(expectedCsv, csvMobills)
    }
}
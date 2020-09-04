package com.github.edallagnol

import com.fasterxml.jackson.databind.ObjectReader
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import java.io.BufferedReader
import java.io.InputStream
import java.io.OutputStream
import java.math.BigDecimal

class InterMobillsConverter(val conta: String? = null) {

    private val mapper = CsvMapperFactory.create()

    fun convert(input: InputStream, output: OutputStream) {
        val csvInterReader = readCsv(input)
        val objectReader = createObjectReader()
        val csvMobills = convertObjects(csvInterReader, objectReader)
        writeOutput(output, csvMobills)
    }

    private fun schemaInter(): CsvSchema {
        return mapper.schemaFor(TransactionInter::class.java)
                .withHeader()
                .withColumnSeparator(';')
                .withColumnReordering(true)
    }

    private fun schemaMobills(): CsvSchema {
        return mapper.schemaFor(TransactionMobills::class.java)
                .withHeader()
                .withColumnSeparator(';')
    }

    private fun convertToMobills(transactionInter: TransactionInter): TransactionMobills {
        return TransactionMobills(
                transactionInter.data,
                transactionInter.estabelecimento,
                BigDecimal(transactionInter.valor.replace(',', '.')).negate().toString(),
                this.conta,
                null
        )
    }

    private fun convertObjects(csvInter: BufferedReader, reader: ObjectReader): Iterable<TransactionMobills> {
        return reader.readValues<TransactionInter>(csvInter)
                .asSequence()
                .map(::convertToMobills)
                .asIterable()
    }

    private fun createObjectReader(): ObjectReader {
        return mapper.readerFor(TransactionInter::class.java)
                .with(schemaInter())
    }

    private fun readCsv(input: InputStream): BufferedReader {
        val csvInter = input.bufferedReader()
        skipLines(csvInter, 5)
        return csvInter
    }

    @Suppress("SameParameterValue")
    private fun skipLines(reader: BufferedReader, lines: Int) {
        repeat(lines) { reader.readLine() }
    }

    private fun writeOutput(output: OutputStream, csvMobills: Iterable<TransactionMobills>) {
        mapper.writer(schemaMobills()).writeValue(output, csvMobills)
    }
}

package com.github.edallagnol

import com.fasterxml.jackson.annotation.JsonProperty

data class TransactionInter (
        @JsonProperty("Data da Transacao") val data: String,
        @JsonProperty("Estabelecimento") val estabelecimento: String,
        @JsonProperty("Tipo da Transacao") val tipo: String,
        @JsonProperty("Valor")  val valor: String
)

data class TransactionMobills (
        @JsonProperty("Data") val data: String,
        @JsonProperty("Descrição") val descricao: String,
        @JsonProperty("Valor") val valor: String,
        @JsonProperty("Conta") val conta: String?,
        @JsonProperty("Categoria") val categoria: String?
)
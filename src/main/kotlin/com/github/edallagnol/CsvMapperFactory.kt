package com.github.edallagnol

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class CsvMapperFactory {
    companion object {
        fun create(): CsvMapper {
            val mapper = CsvMapper();
            mapper.registerKotlinModule()
            mapper.disable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
            return mapper;
        }
    }
}
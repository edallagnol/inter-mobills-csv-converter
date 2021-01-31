package com.github.edallagnol

import org.apache.commons.cli.*


fun main(args: Array<String>) {
    val options = Options()
    val conta = Option("c", "conta", true, "Conta padrão")
    options.addOption(conta)

    val parser: CommandLineParser = DefaultParser()
    try {
        val cmd = parser.parse(options, args)
        val contaDefault = cmd.getOptionValue(conta.opt)
        InterMobillsConverter(contaDefault).convert(System.`in`, System.out);
    } catch (e: ParseException) {
        HelpFormatter().printHelp("InterMobillsCsvConverter", options)
    }


}
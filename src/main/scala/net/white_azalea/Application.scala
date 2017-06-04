package net.white_azalea

import net.white_azalea.parsers.arguments.ArgParser
import net.white_azalea.parsers.junits.JunitResultParser

/**
 * Application entry point.
 *
 * Created by azalea on 2017/06/04.
 */
object Application extends App {

  ArgParser.parse(args).foreach(config => {
    val javaDoc =
      parsers.javadoc.xmlDocletparser
        .parse(config.javaDocXml)

    val testCases =
      JunitResultParser.parse(config.junitResultDir)

    println(testCases)
  })
}

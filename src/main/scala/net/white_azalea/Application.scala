package net.white_azalea

import models.parsers.arguments.parseArgument
import models.parsers.junits.parseJunit
import models.parsers.javadoc.parseJavadoc
import net.white_azalea.models.exporter.Exporter

/**
 * Application entry point.
 *
 * Created by azalea on 2017/06/04.
 */
object Application extends App {
  parseArgument(args).foreach(config => {
    def javaDoc = parseJavadoc(config.javaDocXml)
    def testCases = parseJunit(config.junitResultDir)

    println(Exporter.export(
      Option(config.template),
      javaDoc,
      testCases
    ))
  })
}

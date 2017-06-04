package net.white_azalea.parsers.arguments

import java.io.File

/**
 * Application argument parameter.
 *
 * @param output          Result output file path.
 * @param javaDocXml      JavaDoc XML file path.
 * @param junitResultDir  JUnit test results XML dir path.
 */
case class Config(output: File, javaDocXml: File, junitResultDir: File)
package net.white_azalea.datas.arguments

import java.io.File

/**
 * Application argument parameter.
 *
 * @param template        Result template file path.
 * @param javaDocXml      JavaDoc XML file path.
 * @param junitResultDir  JUnit test results XML dir path.
 */
case class Config(
  template: File,
  javaDocXml: File,
  junitResultDir: File
)
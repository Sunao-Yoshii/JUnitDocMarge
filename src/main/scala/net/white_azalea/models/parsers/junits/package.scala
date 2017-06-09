package net.white_azalea.models.parsers

import java.io.File

/**
 * Junit parser.
 */
package object junits {

  private lazy val parser = new JunitResultParser

  def parseJunit(xmlDir: File) = parser.parse(xmlDir)
}

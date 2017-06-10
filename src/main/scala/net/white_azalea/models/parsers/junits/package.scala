package net.white_azalea.models.parsers

import java.io.File

import net.white_azalea.datas.junit.TestCase

/**
 * Junit parser.
 */
package object junits {

  private lazy val parser = new JunitResultParser

  def parseJunit(xmlDir: File): List[TestCase] =
    parser.parse(xmlDir)
}

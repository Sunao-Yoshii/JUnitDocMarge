package net.white_azalea.parsers.javadoc

import java.io.File

/**
 * Parse java doc xml to scala object.
 *
 * Defined parse interface.
 */
trait JavaDocParser {

  /**
   * Parse XML file to PackageDoc objects.
   *
   * @param xmlFile source file.
   * @return
   */
  def parse(xmlFile: File): List[PackageDoc]
}

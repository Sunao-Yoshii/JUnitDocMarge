package net.white_azalea.models.parsers

import java.io.File

import net.white_azalea.datas.javadoc.PackageDoc

/**
 * Xml parser package.
 */
package object javadoc {

  /**
   * MarkusBernhardt/xml-doclet (var 1.0.5) specific parser.
   */
  private val xmlJavaDocParser = new XmlDocletParser

  /**
    * parse javadoc xml.
    *
    * @param file javadoc XML file.
    * @return
    */
  def parseJavadoc(file: File): List[PackageDoc] =
    xmlJavaDocParser.parse(file)
}

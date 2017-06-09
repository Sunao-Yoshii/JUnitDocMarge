package net.white_azalea.models.parsers.utils

import java.io.File

import scala.io.Source
import scala.xml.Document
import scala.xml.parsing.ConstructingParser

/**
 * Utilities for XML parse.
 */
object XML {

  /**
   * load XML file and parse to Document object.
   *
   * @param file target xml file.
   * @return
   */
  def load(file: File): Document = {
    val s = Source.fromFile(file, "UTF-8")
    try {
      ConstructingParser.fromSource(s, false).document()
    } finally {
      s.close()
    }
  }
}

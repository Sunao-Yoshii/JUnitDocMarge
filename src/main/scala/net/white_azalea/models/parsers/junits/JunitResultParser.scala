package net.white_azalea.models.parsers.junits

import java.io.{File, FilenameFilter}

import net.white_azalea.datas.junit.{TestCase, TestFailure}
import net.white_azalea.models.parsers.utils.XML

import scala.xml.Node

class JunitResultParser {

  def parse(xmlDir: File): Seq[TestCase] = {
    val xmlFiles = xmlDir.listFiles(new FilenameFilter {
      override def accept(dir: File, name: String) = name.endsWith(".xml")
    })

    xmlFiles
      .toSeq
      .flatMap(parseSingleFile)
  }

  private[junits] def parseSingleFile(file: File): Seq[TestCase] = {
    (XML.load(file) \ "testcase").map(readTestCase)
  }

  private[junits] def readTestCase(node: Node): TestCase = {
    val methodName = node \@ "name"
    val className = node \@ "classname"
    val failures = (node \ "failure").map(readFailure)
    TestCase(methodName, className, failures)
  }

  private[junits] def readFailure(node: Node): TestFailure = {
    val message = node \@ "message"
    val typeMessage = node \@ "type"
    val cause = node.toString()
    TestFailure(message, typeMessage, cause)
  }
}

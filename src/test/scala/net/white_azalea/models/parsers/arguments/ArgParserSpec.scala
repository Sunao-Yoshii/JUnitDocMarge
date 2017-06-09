package net.white_azalea.models.parsers.arguments

import java.io.File

import org.specs2.Specification

/**
  * Created by azalea on 2017/06/04.
  */
class ArgParserSpec extends Specification {
  def is =
    s2"""
This specification check argument parser.

The 'parse' method should
  parse method will good to parse 2 args         $r1
  parse arg1 to javaDocXml(as java.io.File)      $r2
  parse arg1 to junitResultDir(as java.io.File)  $r3
  return none if args are bad                    $b1
  return none if args1 are bad                   $b2
  return none if arg2 are bad                    $b3
                                                 """

  val goodArguments = Array(
    "src/test/resources/javadoc/javadoc.xml", "src/test/resources/ut")

  def r1 = ArgParser.parse(goodArguments).isDefined must beTrue
  def r2 = ArgParser.parse(goodArguments).get.javaDocXml must beAnInstanceOf[File]
  def r3 = ArgParser.parse(goodArguments).get.junitResultDir must beAnInstanceOf[File]

  val badJavaDoc = Array(
    "src/test/resources/javadoc/not_xml.txt",
    "src/test/resources/ut")
  val badJUnit   = Array(
    "src/test/resources/javadoc/not_xml.txt",
    "src/test/resources/javadoc/not_xml.txt")

  def b1 = ArgParser.parse(Array()).isEmpty must beTrue
  def b2 = ArgParser.parse(badJavaDoc).isEmpty must beTrue
  def b3 = ArgParser.parse(badJUnit).isEmpty must beTrue
}

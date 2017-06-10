package net.white_azalea.models.parsers.arguments

import net.white_azalea.datas.arguments.Config
import org.specs2.Specification
import org.specs2.matcher.MatchResult

/**
 * Test for argument parser.
 */
class ArgParserSpec extends Specification {
  def is =
    s2"""
This specification check argument parser.

The 'parse' method should
  parse 2 arguments as File[file] and File[dir]:     $simple
  javadoc file arg must be exists:                   $arg1exists
  javadoc file arg must be file:                     $arg1dir
  javadoc file arg must be xml extension:            $arg1txt
  junitDir arg must not be empty:                    $arg2empty
  junitDir arg must be exists:                       $arg2exists
  junitDir arg must be directory:                    $arg2file
  default template option is null:                   $defaultTemplate
                                                 """

  def parse(args: Seq[String]) =
    new ArgParser().parse(args, Config(null, null, null))

  val testJavadocXmlFile = "src/test/resources/javadoc/javadoc.xml"
  val testUtResultDir = "src/test/resources/ut"
  val dummyTextFile = "src/test/resources/javadoc/not_xml.txt"

  def simple: MatchResult[Any] =
    parse(Seq(testJavadocXmlFile, testUtResultDir))
      .map(v => v.javaDocXml.exists() && v.junitResultDir.exists()) must_== Some(true)

  def arg1exists = parse(Seq("nothing/file/path.xml", testUtResultDir)) must_== None

  def arg1dir = parse(Seq(testUtResultDir, testUtResultDir)) must_== None

  def arg1txt = parse(Seq(dummyTextFile, testUtResultDir)) must_== None

  def arg2empty = parse(Seq(dummyTextFile)) must_== None

  def arg2exists = parse(Seq(testJavadocXmlFile, "nothing/dir")) must_== None

  def arg2file = parse(Seq(testJavadocXmlFile, dummyTextFile)) must_== None

  def defaultTemplate = parse(Seq("-t", "src/main/resources/template.mustache", testJavadocXmlFile, testUtResultDir))
    .map(_.template != null) must_== Some(true)
}

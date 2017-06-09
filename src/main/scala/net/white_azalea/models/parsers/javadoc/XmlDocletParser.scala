package net.white_azalea.models.parsers.javadoc
import java.io.File

import net.white_azalea.datas.javadoc.{ClassDoc, MethodDoc, PackageDoc}
import net.white_azalea.models.parsers.utils.XML

import scala.xml.Node

/**
 * A javadoc parser that specified MarkusBernhardt/xml-doclet (var 1.0.5).
 */
class XmlDocletParser {

  /**
   * Parse XML file to PackageDoc objects.
   *
   * @param xmlFile source file.
   * @return
   */
  def parse(xmlFile: File): List[PackageDoc] = {
    try {
      val document = XML.load(xmlFile)
      (document \ "package").flatMap(readPackage).toList
    } catch {
      case e => {
        println(s"Failed to load resource ${xmlFile.getAbsolutePath}.\nCause : ${e.getMessage}")
        throw e
      }
    }
  }

  /**
   * Parse package XML element.
   *
   * @param packageNode package element node.
   * @return
   */
  private[javadoc] def readPackage(packageNode: Node): Option[PackageDoc] = {
    val name = packageNode \@ "name"
    val comment = findNode(packageNode, "comment").map(_.text)
    val classes = (packageNode \ "class").flatMap(readClass)

    if (classes.isEmpty) {
      None
    } else {
      Some(PackageDoc(name, comment, classes.toList))
    }
  }

  /**
   * Parse class XML element.
   *
   * @param classNode class element node.
   * @return
   */
  private[javadoc] def readClass(classNode: Node): Option[ClassDoc] = {

    if (isSkipClass(classNode)) {

      None
    } else {

      val name = classNode \@ "name"
      val qualifiedName = classNode \@ "qualified"
      val comment = findNode(classNode, "comment").map(_.text)
      val methods = (classNode \ "method").flatMap(readMethod)

      if (methods.isEmpty) {
        None
      } else {
        Some(ClassDoc(name, qualifiedName, comment, methods.toList))
      }
    }
  }

  /**
   * Judge read skip class definition.
   *
   * Skip reasons.
   *
   * - is abstract
   * - is not public
   *
   * All reasons that can't run at JUnit.
   *
   * @param classNode class XML node.
   * @return
   */
  private[javadoc] def isSkipClass(classNode: Node): Boolean = {
    classNode \@ "abstract" == "true" ||
      classNode \@ "scope" != "public"
  }

  /**
   * read test method definitions.
   *
   * @param node method definition XML element.
   * @return
   */
  private[javadoc] def readMethod(node: Node): Option[MethodDoc] = {
    if (isSkipMethod(node)) {
      None
    } else {
      val name = node \@ "name"
      val qualifiedName = node \@ "qualified"
      val comment = findNode(node, "comment").map(_.text)

      Some(MethodDoc(name, qualifiedName, comment))
    }
  }

  /**
   * Judge read skip method definition.
   *
   * Skip reasons.
   *
   * - not specified by org.junit.Test annotation.
   *
   * @param node method element.
   * @return
   */
  private[javadoc] def isSkipMethod(node: Node): Boolean = {
    val isTestCase = (node \ "annotation").exists(n => {
      val annotateClass = n \@ "qualified"
      annotateClass == "org.junit.Test"
    })

    !isTestCase
  }

  /**
   * read head child tag.
   *
   * @param node
   * @param tagName
   * @return
   */
  private[javadoc] def findNode(node: Node, tagName: String): Option[Node] = {
    (node \ tagName).headOption
  }
}

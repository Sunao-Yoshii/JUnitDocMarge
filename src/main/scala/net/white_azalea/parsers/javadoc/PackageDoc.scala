package net.white_azalea.parsers.javadoc

/**
 * Parse result of javaDoc package
 *
 * @param name     package name.
 * @param comment  package comment.
 * @param classes  contains class definitions.
 */
case class PackageDoc(
  name: String,
  comment: Option[String],
  classes: List[ClassDoc]
) extends Commented

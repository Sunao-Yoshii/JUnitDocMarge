package net.white_azalea.parsers.javadoc

case class MethodDoc(
  name: String,
  qualifiedName: String,
  comment: Option[String]
) extends Commented

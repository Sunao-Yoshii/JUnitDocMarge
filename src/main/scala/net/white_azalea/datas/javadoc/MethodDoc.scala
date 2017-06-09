package net.white_azalea.datas.javadoc

case class MethodDoc(
  name: String,
  qualifiedName: String,
  comment: Option[String]
) extends Commented

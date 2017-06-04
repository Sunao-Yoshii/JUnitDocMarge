package net.white_azalea.parsers.javadoc

/**
 * Comment tag contained object.
 *
 * Comment may contains summary and description.
 * This class are parse same value.
 */
trait Commented {

  def comment: Option[String]

  def summary: Option[String] = comment.flatMap(_.split("¥¥.").headOption)

  def description: Option[String] = {
    comment.flatMap(m => {
      val desc = m.split("¥¥.").tails.mkString(".")
      if (desc.trim.isEmpty) None else Option(desc)
    })
  }
}

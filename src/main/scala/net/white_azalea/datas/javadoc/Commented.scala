package net.white_azalea.datas.javadoc

/**
 * Comment tag contained object.
 *
 * Comment may contains summary and description.
 * This class are parse same value.
 */
trait Commented {

  /**
   * Javadoc comment.
   *
   * this comment contains All of javadoc message.
   * @return
   */
  def comment: Option[String]

  /**
   * Return summary of method or class.
   *
   * return head line of javadoc comment.
   * @return
   */
  def summary: Option[String] =
    comment.flatMap(_.split("\\.").headOption)

  /**
   * Return description of method or class.
   *
   * return tail lines of javadoc comment.
   * @return
   */
  def description: Option[String] = {
    comment.flatMap(m => {
      val desc = m.split("\\.").drop(1).mkString(".\n") // drop first line.
      val isEmptyString = desc.replaceAll("\\.", "").trim.isEmpty // is no contents?
      if (isEmptyString) None else Option(desc)
    })
  }
}

package net.white_azalea.datas.junit

case class TestFailure(
  message: String,
  typeSting: String,
  detail: String
)

case class TestCase(
    name: String,
    qualifiedClassName: String,
    failures: Seq[TestFailure]
) {

  def isSuccess: Boolean =
    failures.isEmpty

  def className: String =
    qualifiedClassName.split("\\.").last

  def packageName: String =
    qualifiedClassName.split("\\.").dropRight(1).mkString(".")
}

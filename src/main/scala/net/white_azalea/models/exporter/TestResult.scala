package net.white_azalea.models.exporter

import net.white_azalea.datas.javadoc.{ ClassDoc, MethodDoc, PackageDoc }
import net.white_azalea.datas.junit.{ TestCase, TestFailure }

case class TestResult(
    name: String,
    className: String,
    packageName: String,
    summary: String,
    description: String,
    errors: Seq[TestFailure]
) {
  val isSuccess = errors.isEmpty
  val isFailed = !isSuccess
}

case class TestClassResult(
  name: String,
  packageName: String,
  summary: String,
  description: String,
  tests: Seq[TestResult]
)

case class TestPackageResult(
  name: String,
  summary: String,
  description: String,
  testClasses: Seq[TestClassResult]
)

object TestResult {

  def parse(
    packageName: String,
    className: String,
    testCases: Seq[TestCase],
    methodDocs: Seq[MethodDoc]
  ): Seq[TestResult] = {
    testCases
      .map(testCase => {
        val methodDoc = methodDocs.find(_.name == testCase.name)
        val name = testCase.name
        val summary = methodDoc.flatMap(_.summary).getOrElse("")
        val description = methodDoc.flatMap(_.description).getOrElse("")
        def errors = testCase.failures

        TestResult(name, className, packageName, summary, description, errors)
      })
  }
}

object TestClassResult {

  def parse(packageName: String, testCases: Seq[TestCase], classDocs: Seq[ClassDoc]): Seq[TestClassResult] = {
    testCases
      .map(_.name)
      .distinct
      .map(name => {
        val classDoc = classDocs.find(_.name == name)
        val summary = classDoc.flatMap(_.summary).getOrElse("")
        val description = classDoc.flatMap(_.description).getOrElse("")
        val tests = TestResult.parse(
          packageName, name,
          testCases.filter(_.className == name),
          classDoc.map(_.methods).getOrElse(Nil)
        )

        TestClassResult(name, packageName, summary, description, tests)
      })
  }
}

object TestPackageResult {

  def parse(testCases: Seq[TestCase], packageDocs: Seq[PackageDoc]): Seq[TestPackageResult] = {
    testCases
      .map(_.packageName)
      .distinct
      .map(packageName => {
        val packageDoc = packageDocs.find(_.name == packageName)
        val summary = packageDoc.flatMap(_.summary).getOrElse("")
        val detail = packageDoc.flatMap(_.description).getOrElse("")
        val testClasses = TestClassResult.parse(
          packageName,
          testCases.filter(_.packageName == packageName),
          packageDoc.map(_.classes).getOrElse(Nil)
        )

        TestPackageResult.apply(packageName, summary, detail, testClasses)
      })
  }
}

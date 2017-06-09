package net.white_azalea.exporter

import java.io.File

import net.white_azalea.datas.javadoc.PackageDoc
import net.white_azalea.datas.junit.TestCase

/**
 * Export result file.
 */
object Exporter {

  /**
   * Export test result file.
   *
   * @param file
   * @param docs
   * @param results
   */
  def export(file: Option[File], docs: => List[PackageDoc], results: => List[TestCase]): Unit = {
    // convert to TestPackageList.
    val result = TestPackageResult.parse(results, docs)

    // init template.

    // render to file.
  }
}

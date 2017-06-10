package net.white_azalea.models.exporter

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
  def export(file: Option[File], docs: => List[PackageDoc], results: => List[TestCase]): String = {
    // convert to TestPackageList.
    val result = TestPackageResult.parse(results, docs)

    // init template.
    val template = new Template(file)

    // render to file.
    template.render(result)
  }
}

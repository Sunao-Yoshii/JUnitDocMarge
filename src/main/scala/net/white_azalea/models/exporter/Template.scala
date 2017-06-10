package net.white_azalea.models.exporter

import java.io.File

import org.fusesource.scalate.{ TemplateEngine, TemplateSource }

/**
 * Template render.
 */
class Template(templateFile: Option[File]) {

  /**
   * Template engine.
   *
   * mode as mustache.
   * http://scalate.github.io/scalate/documentation/mustache.html
   */
  private val templateEngine = new TemplateEngine(mode = "mustache")

  /**
   * Render to template.
   *
   * @param value render data.
   * @return
   */
  def render(value: Seq[TestPackageResult]): String = {

    def mapValue: Map[String, Any] = Map("package" -> value)

    templateFile.map(file => {
      templateEngine.layout(file.getAbsolutePath, mapValue)
    }).getOrElse({
      val url = getClass.getClassLoader.getResource("template.mustache")
      val template = TemplateSource.fromURL(url)
      templateEngine.layout(template, mapValue)
    })
  }
}

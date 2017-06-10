package net.white_azalea.models.parsers

import net.white_azalea.datas.arguments.Config

/**
 * Console command argument parser.
 */
package object arguments {

  /**
   * parse arguments to Config instance.
   *
   * this method return net.white_azalea.datas.arguments.Config if the arguments are valid.
   * or return None.
   *
   * usage:
   * {{
   *   import net.white_azalea.models.parsers.*
   *
   *   public static void main(args: Array[String]) {
   *     val config: Option[Config] = parseArgument(args)
   * }}
   *
   * @see net.white_azalea.datas.arguments.Config
   * @param args command line arguments.
   * @return return Some(Config) if arguments are valid.
   */
  def parseArgument(args: Array[String]): Option[Config] = {
    (new ArgParser).parse(args, Config(null, null, null))
  }
}

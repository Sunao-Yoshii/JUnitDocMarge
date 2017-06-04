package net.white_azalea.parsers.javadoc

/**
 * Definitions for class javadoc.
 *
 * @param name           class simple name.
 * @param qualifiedName  qualified class name.
 * @param comment        this class summary.
 * @param methods        method definitions.
 */
case class ClassDoc(
  name: String,
  qualifiedName: String,
  comment: Option[String],
  methods: List[MethodDoc]
) extends Commented

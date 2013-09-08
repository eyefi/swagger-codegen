import com.wordnik.swagger.codegen.BasicObjcGenerator

object EyeFiObjcCodegen extends BasicObjcGenerator {
  def main(args: Array[String]) = generateClient(args)

  // location of templates
  override def templateDir = "src/main/resources/eyefi-objc"

  // where to write generated code
  override def destinationDir = "client/objc/client"

  // naming for the models
  override def toModelName(name: String) = {
    (typeMapping.values ++ 
      foundationClasses ++ 
      importMapping.values ++ 
      defaultIncludes ++ 
      languageSpecificPrimitives
    ).toSet.contains(name) match {
      case true => name(0).toUpper + name.substring(1)
      case _ => name.indexOf("EyeFi") match {
        case 0 => name;
        case _ => "EyeFi" + name(0).toUpper + name.substring(1)
      }
    }
  }

  override def toModelFilename(name: String) = {
    name.indexOf("EyeFi") match {
      case 0 => name
      case _ => "EyeFi" + name
    }
  }

  // naming for the apis
  override def toApiName(name: String) = "EyeFi" + name(0).toUpper + name.substring(1) + "Api"

  // types that don't need to be included
  override def defaultIncludes = Set(
    "bool",
    "int",
    "integer",
    "NSString",
    "NSObject", 
    "NSArray",
    "NSNumber",
    "Date")

  // supporting classes
  override def supportingFiles =
    List(
      ("NIKSwaggerObject.h", destinationDir, "NIKSwaggerObject.h"),
      ("NIKSwaggerObject.m", destinationDir, "NIKSwaggerObject.m"),
      ("NIKApiInvoker.h", destinationDir, "NIKApiInvoker.h"),
      ("NIKApiInvoker.m", destinationDir, "NIKApiInvoker.m"),
      ("NIKDate.h", destinationDir, "NIKDate.h"),
      ("NIKDate.m", destinationDir, "NIKDate.m"),
      ("NIKFile.h", destinationDir, "NIKFile.h"),
      ("NIKFile.m", destinationDir, "NIKFile.m"))

}

import com.wordnik.swagger.codegen.BasicJavaGenerator
import java.io.{ File, FileWriter, InputStream }

object EyeFiJavaCodegen extends BasicJavaGenerator {
  def main(args: Array[String]) = generateClient(args)

  // location of templates
  override def templateDir = "src/main/resources/Java"

  // where to write generated code
  override def destinationDir = "client/java/src/main/java"

  // api invoker package
  override def invokerPackage = Some("com.eyefi.client.common")

  // package for models
  override def modelPackage = Some("com.eyefi.client.model")

  // package for api classes
  override def apiPackage = Some("com.eyefi.client.api")

   additionalParams ++= Map(
    "artifactId" -> "eyefi",
    "artifactVersion" -> "1.0.0",
    "groupId" -> "com.eyefi")

  // supporting classes
  override def supportingFiles = List(
    ("apiInvoker.mustache", destinationDir + java.io.File.separator + invokerPackage.get.replace(".", java.io.File.separator) + java.io.File.separator, "ApiInvoker.java"),
    ("JsonUtil.mustache", destinationDir + java.io.File.separator + invokerPackage.get.replace(".", java.io.File.separator) + java.io.File.separator, "JsonUtil.java"),
    ("apiException.mustache", destinationDir + java.io.File.separator + invokerPackage.get.replace(".", java.io.File.separator) + java.io.File.separator, "ApiException.java"),
    ("pom.mustache", destinationDir, "pom.xml")
  )
}

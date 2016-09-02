package jfalkner.cc2csv

import java.time.Instant
import java.util.UUID

import reflect._
import scala.reflect.runtime.{currentMirror => cm}
import scala.reflect.runtime.universe._

/**
  * Simple conversion of case classes to and from CSV
  *
  * 1. Make a case class. For example, <code>case class Foo(a: String, b: Int, c: Instant)<code>.
  *
  * 2. Use marshall(foo) to convert it to CSV: <code>Bar,123,2016-09-02T03:38:08.917Z</code>
  *
  * 3. Use unmarshall[Foo]("Bar,123,2016-09-02T03:38:08.917Z") to convert it back to case class Foo
  *
  * This API tries to keep it simple and work for most cases, including commonly used classes with non-trivial
  * conversion to a string and back. For example, such as Instant.
  *
  * Don't expect this to work on crazy graphs of objects. Expect it to work on simple case classes that are convenient,
  * typed tuples commonly used in Scala.
  */
object Csv {

  def unmarshall[A](line: String)(implicit t: ClassTag[A]): A = {
    val claas = cm.classSymbol(t.runtimeClass)
    val modul = claas.companion.asModule
    val im = cm reflect (cm reflectModule modul).instance

    val args = line.split(",").zip(claas.primaryConstructor.asMethod.paramLists.head.map(_.typeSignature)).map{
      case (v: String, t) if t =:= typeOf[String] => v
      case (v: String, t) if t =:= typeOf[Int] => v.toInt
      case (v: String, t) if t =:= typeOf[Long] => v.toLong
      case (v: String, t) if t =:= typeOf[Float] => v.toFloat
      case (v: String, t) if t =:= typeOf[Double] => v.toDouble
      case (v: String, t) if t =:= typeOf[Boolean] => v.toBoolean
      case (v: String, t) if t =:= typeOf[UUID] => UUID.fromString(v)
      case (v: String, t) if t =:= typeOf[Instant] => Instant.parse(v)
    }.asInstanceOf[Array[Object]]

    val at = TermName("apply")
    val ts = im.symbol.typeSignature
    val method = (ts member at).asMethod
    (im reflectMethod method)(args: _*).asInstanceOf[A]
  }

  def marshall[P <: Product](caseClass: P): String = {
    caseClass.productIterator.map{
      case v: Instant => v.toString
      case v => v.toString
    }.mkString(",")
  }
}

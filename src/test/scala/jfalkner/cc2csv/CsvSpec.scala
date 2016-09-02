package jfalkner.cc2csv

import java.time.Instant

import org.specs2.mutable.Specification
import Csv._

/**
  * Created by jfalkner on 9/1/16.
  */
class CsvSpec extends Specification {

  val string = "Bar"
  val int = 123
  val instantString = "2016-09-02T03:49:08.088Z"
  val instant = Instant.parse(instantString)

  val foo = Foo(string, int, instant)
  val fooString = s"$string,$int,$instantString"

  "CSV Conversion" should {
    "marshall Foo() example correctly" in {
      fooString mustEqual marshall(foo)
    }
    "unmarshall Foo() example correctly" in {
      foo mustEqual unmarshall[Foo](fooString)
    }
  }
}

case class Foo(a: String, b: Int, c: Instant)
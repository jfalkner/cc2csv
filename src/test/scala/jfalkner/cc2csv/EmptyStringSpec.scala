package jfalkner.cc2csv

import org.specs2.mutable.Specification
import Csv._

/**
  * Tests that split(",", -1) is correctly in use vs split(",")
  *
  * Java's split method by default will drop blank values, which is a problem for this API since it needs to match
  * the expected number and order of parameters to create case classes.
  */
class EmptyStringSpec extends Specification {

  "Blank strings" should {
    "Should marshall to empty values" in {
      ",," mustEqual marshall(CanHaveEmptyStrings("", "", ""))
    }
    "Should not result in too few arguments" in {
      CanHaveEmptyStrings("", "", "") mustEqual unmarshall[CanHaveEmptyStrings](",,")
    }
  }
}

case class CanHaveEmptyStrings(a: String, b: String, c: String)
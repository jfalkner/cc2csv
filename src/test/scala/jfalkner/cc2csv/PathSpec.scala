package jfalkner.cc2csv

import java.nio.file.{Path, Paths}

import jfalkner.cc2csv.Csv._
import org.specs2.mutable.Specification

/**
  * Tests that converting Path to a String and back works
  */
class PathSpec extends Specification {

  val testFile = "/temp/test.file"
  val hasPath = HasPath(Paths.get(testFile))

  "Path support" should {
    "Should marshall" in {
      testFile mustEqual marshall(hasPath)
    }
    "Should unmarshall" in {
      hasPath mustEqual unmarshall[HasPath](testFile)
    }
  }
}

case class HasPath(a: Path)
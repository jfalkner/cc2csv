# Scala Case Class to CSV API

A simple Scala API for converting commonly used Scala case classes to 
CSV and back. Case classes are convenient to use in your code. Sometimes
it is also helpful to have an easy way to convert values to and from an
easily debuggable and editable string. That is what this API provides.

Use this API as follows.

```
# 1. Make a case class
case class Foo(a: String, b: Int, c: Instant)

# 2. Convert it to CSV with `marshall`
val line = marshall(Foo("Bar",123,Instant.now()))

# 3. Convert the text back to CSV with `unmarshall`
val foo = unmarshall[Foo](line)

println(line)
# "Bar,123,2016-09-02T03:49:08.088Z"
println(foo)
# Foo(Bar,123,2016-09-02T03:49:08.088Z)
```

## Dependencies

Make sure you have `scala-reflect` in your SBT dependencies and are 
using scala 2.10+.

```
libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)
```
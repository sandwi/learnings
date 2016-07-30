// Option, None, Some
// Scala encourages you to use the Option type for variables and function
// return values when they may or may not refer to a value.
// When there is no value, use None, an object that is a subclass of Option.
// When there is a value, use Some, which wraps the value. Some is also a subclass of Option.
var s = Some("abc")          // Scala can infer the type
var t: Option[String] = None // Type must be explicit
if (t isDefined) println(t)
if (t isEmpty) println("Nothing here!")

val w = t.getOrElse("Nothing here!")

val a:Option[Int] = Some(5)
val b:Option[Int] = None

println("a.getOrElse(0): " + a.getOrElse(0) )
println("b.getOrElse(10): " + b.getOrElse(10) )

// Option with Collections
// Map
val stateCapitals = Map(
    "Alabama" -> "Montgomery",
    "Alaska" -> "Juneau",
    // ...
    "Wyoming" -> "Cheyenne")
def show(x: Option[String]) = x match {
    case Some(s) => s
    case None => "?"
}
println( "Get the capitals wrapped in Options:" )
println( "Alabama: " + stateCapitals.get("Alabama") )
println( "Wyoming: " + stateCapitals.get("Wyoming") )
println( "Unknown: " + stateCapitals.get("Unknown") )
println( "Get the capitals themselves out of the Options:" )
println( "Alabama: " + stateCapitals.get("Alabama").get )
println( "Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!") )
println( "Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!") )
println("show(stateCapitals.get( \"Alabama\")) : " + show(stateCapitals.get( "Alabama")) )
println("show(stateCapitals.get( \"Virginia\")) : " + show(stateCapitals.get( "Virginia")) )

val opt = Option(1)
val a = opt match {
    case Some(x) => x + 1
    case None => "a"
}

val b = opt map (_ + 1) getOrElse "a"

//val c = opt.fold("a")(_ + 1)

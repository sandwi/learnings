val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska" -> "Juneau",
  // ...
  "Wyoming" -> "Cheyenne")

// Traverse Map with closure or higger order functions
stateCapitals foreach { kv => println(kv._1 + ": " + kv._2) }
println( "Get the capitals wrapped in Options:" )
println( "Alabama: " + stateCapitals.get("Alabama") )
println( "Wyoming: " + stateCapitals.get("Wyoming") )
println( "Unknown: " + stateCapitals.get("Unknown") )
println( "Get the capitals themselves out of the Options:" )
println( "Alabama: " + stateCapitals.get("Alabama").get )
println( "Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!") )
println( "Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!") )
// Map or convert or transform from one map to another
// Iterable.map creates and returns an ArrayBuffer as the new Iterable collection
// lengths is of type ArrayBuffer
val lengths = stateCapitals map { kv => (kv._1, kv._2.length) }
println(lengths)
// Here map2 is a map
val map2 = stateCapitals map { kv => (kv._1, kv._2.length) }
// ++ operator is defined in Map and it adds map2
val lengths2 = Map[String,Int]() ++ map2
println(lengths2)
// FIltering
val map3 = stateCapitals filter { kv => kv._1 startsWith "A" }
println( map3 )
// Maps are also functions
stateCapitals("Alabama")
// Get returns an Option where maps as function does not
stateCapitals get "Alabama"
def showCapital(capital: String) = stateCapitals.get(capital) match {
  case Some(capital) => capital
  case None => "missing data"
}
showCapital("Alabama")
showCapital("XXXYYY")
// Polynomials
class Poly(terms0: Map[Int, Double]) {
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue(0.0)
  /*
  def + (other: Poly) = {
      new Poly(terms ++ other.terms map adjust)
  }
  */
  def adjust1(term: (Int, Double)) : (Int, Double) = {
    val (exp, coeff) = term
    terms get exp match {
      case Some(coeff1) => exp -> (coeff + coeff1)
      case None => exp -> coeff
    }
  }
  // better adjust
  def adjust(term: (Int, Double)) : (Int, Double) = {
    val (exp, coeff) = term
    exp -> (coeff + terms(exp))
  }

  // An efficient + with fold left
  def + (other: Poly) = new Poly((other.terms foldLeft terms)(addTerms))

  def addTerms(terms: Map[Int, Double], term: (Int, Double)): Map[Int,Double] = {
    val (exp, coeff) = term
    terms + (exp -> (coeff + terms(exp)))
  }

  override def toString =
    (for( (exp, coeff) <- terms.toList.sorted.reverse) yield coeff + "x^" + exp) mkString " + "
}

val p1 = new Poly(Map(1 -> 2.0, 3 -> 4.0, 5 -> 6.2))
val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))

p1+p2
val p3 = new Poly((1,2.0), (3,4.0), (5,6.2))
val p4 = new Poly((0 -> 3.0), (3 -> 7.0))
p3+p4
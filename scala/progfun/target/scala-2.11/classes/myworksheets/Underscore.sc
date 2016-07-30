// imports all the classes in the package matching
import java.util
import scala.math._
// imports all the members of the object Fun.
// (static import in java)
import scala.collection.mutable.ListBuffer._

// imports all the members of the object Fun but renames Foo to Bar
//import com.test.Fun.{ Foo => Bar , _ }
// imports all the members except Foo. To exclude a member rename it to _
//import com.test.Fun.{ Foo => _ , _ }
import scala.collection.immutable.List
def matchTest(x: Int): String = x match {
  case 1 => "one"
  case 2 => "two"
  case _ => "anything other than one and two"
}
println(matchTest(3))
println(matchTest(1))
def matchCollection(expr: Any) = expr match {
  case List(1,_,_) => " a list with three element and the first element is 1"
  case List(_*) => " a list with zero or more elements "
  //case Map[_,_] => " matches a map with any key type and any value type "
  case _ =>
}

println(matchCollection(List(1,2,3)))
println(matchCollection(List(2,3)))
// Anonymous Functions
// _ acts as a placeholder for parameters in the anonymous function
List(1,2,3,4,5).foreach(print(_))
List(1,2,3,4,5).foreach( a => print(a))
val sum = List(1,2,3,4,5).reduceLeft(_+_)
val sum2 = List(1,2,3,4,5).reduceLeft((a, b) => a + b)
// In scala, a getter and setter will be implicitly defined for
//  all non-private var in a object. The getter name is same as
//  the variable name and _= is added for setter name
class Test {
  private var a = 0
  def age = a
  // Setter
  def age_=(n:Int) = {
    require(n>0)
    a = n
  }
}
val t = new Test
t.age = 5
println(t.age)
// use _ after the function name to assign it to another variable.
// Makes assigning function1 to another function2
class Test2 {
  def fun = {
    println("fun")
  }

  val funLike = fun _
}

val t2 = new Test2



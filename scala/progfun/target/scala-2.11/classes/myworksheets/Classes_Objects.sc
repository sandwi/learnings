// Singleton in Scala
// Scala classes cannot have static variables or methods.
// Instead a Scala class can have what is called a singleton object, or sometime a companion object
object Main {
  def sayHi() {
    println("Hi!")
  }
}
Main.sayHi()

// Companion Object
// When a singleton object is named the same as a class, it is called a companion object.
// A companion object must be defined inside the same source file as the class.
class Main {
  def sayHelloWorld() {
    println("Hello World")
  }
}
var main : Main = new Main()
main.sayHelloWorld()

// Class Fields, Methods
// Class fields are declared using constructor, no need to declare class fields
// All constructor parameters become private instance vals - immutable fields
class Person(name: String, age: Int) {
  def isOverAge = age > 21
  override def toString = name + ", " + age
}
val p = new Person("Joe", 23)
println(p)

// To make fields mutable, declare constructor parameters var
// Declaring constructor parameter var makes it public
class Person1(var name: String, var age: Int) {
  def isOverAge = age > 21
  def grow(n: Int) = age + n

  override def toString = name + ", " + age
}
val p1 = new Person1("Joe", 23)
println(p1)
p1.name = "Tom"
p1.age = 50
println(p1)
// Class field is mutable but private
// Mutating requires special Scala syntax
class Person2(private var _name: String, private var _age: Int) {
  // accessors
  def name = _name
  def age = _age
  //mutators
  def name_=(aName: String) = {_name = aName}
  def age_=(aAge: Int) = {_age = aAge}

  def isOverAge = age > 21
  def grow(n: Int) = age + n

  override def toString = name + ", " + age
}

val p2 = new Person2("Joe", 33)
println(p2)
p2.name = "John"
p2.age = 45
println(p2)
// Use BeanProperty if JavaBean style is needed
import scala.beans.BeanProperty
class Person3(@BeanProperty var firstName: String,
             @BeanProperty var lastName: String,
             @BeanProperty var age: Int) {
  //override def toString: String = return format("%s, %s, %d", firstName, lastName, age)
}

class EmailAccount {
  @BeanProperty var accountName: String = null
  @BeanProperty var username: String = null
  @BeanProperty var password: String = null
  @BeanProperty var mailbox: String = null
  @BeanProperty var imapServerUrl: String = null
  @BeanProperty var minutesBetweenChecks: Int = 0
  @BeanProperty var protocol: String = null
  @BeanProperty var usersOfInterest = new java.util.ArrayList[String]()
}

// Case Classes
// Scala compiler automatically defines: equals, hashcode, toString
// Defaults to private val - immutable fields
// Can be made var - public and mutable
// or private var - mutable but will require mutator methods
// fundamental use if for data objects and pattern matching
case class Person4(firstName: String, lastName: String, age: Int)
val p4 = new Person4("Joe", "Ma", 24)
println(p4)
// An implementation of Set
object IntSets {
  println("Welcome to Scala Classes!")
  val t1 = new NonEmpty(3,  Empty, Empty)
  val t2 = t1 incl 4
}
val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4
val t3 = t2 incl 5
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
  override def toString = "."
}
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
  override def toString = "{" + left + elem + right + "}"
}
// Lists in Scala
trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}
class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}
class Nil[T] extends List[T] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}
object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T] = new Nil
}
// Generic Functions
def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton[Int](1)
singleton[Boolean](true)
def nth[T](n: Int, xs: List[T]): T =
  if (xs.isEmpty) throw new IndexOutOfBoundsException
  else if (n == 0) xs.head
  else nth(n -1, xs.tail)
val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
nth(2, list)

// Tuples
// expression t._N retrieves the N item, starting at 1, not 0
def tupleator(x1: Any, x2: Any, x3: Any) = (x1, x2, x3)
val t = tupleator("Hello", 1, 2.3)
println( "Print the whole tuple: " + t )
println( "Print the first item: " + t._1 )
println( "Print the second item: " + t._2 )
println( "Print the third item: " + t._3 )

// Lazy Vals
// 1. val is executed when it is defined whereas a lazy val is executed when
// it is accessed the first time
// 2. In contrast to a method (defined with def) a lazy val is executed once and then never again.
// Lazy val is great for when an operation takes long time to complete and
// when it is not sure if it is later used or if it will be used at all.
// 3. if the initialization of a lazy val throws an exception,
// it will attempt to reinitialize the val at next access

lazy val number = { println("Printing!"); 1 + 2 }

val x = { print ("foo, ") ; 10 }
print ("bar, ")
println (x)
lazy val x2 = { print ("foo, ") ; 10 }
print ("bar, ")
println (x2)

trait AbstractT2 {
  println("In AbstractT2:")
  val value: Int
  lazy val inverse = { println("initializing inverse:"); 1.0/value }
  //println("AbstractT2: value = "+value+", inverse = "+inverse)
}
val c2d = new AbstractT2 {
  println("In c2d:")
  val value = 10
}
println("Using c2d:")
println("c2d.value = "+c2d.value+", inverse = "+c2d.inverse)



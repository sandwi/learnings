trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

object exprs {
  def show(e: Expr): String = e match {
    case Number(x) => x.toString

    case Sum(l, r) =>   show(l) + " + " + show(r)
  }
}

exprs.show(Sum(Number(1), Number(44)))

val bools = List(true, false)
for (bool <- bools) {
  bool match {
    case true => println("heads")
    case false => println("tails")
    case _ => println("something other than heads or tails (yikes!)")
  }
}

val randomInt = new scala.util.Random().nextInt(10)
randomInt match {
  case 7 => println("lucky seven!")
  case otherNumber => println("boo, got boring ol' " + otherNumber)
}

// pattern matching on type
val sundries = List(23, "Hello", 8.5, 'q')
for (sundry <- sundries) {
  sundry match {
    case i: Int => println("got an Integer: " + i)
    case s: String => println("got a String: " + s)
    case f: Double => println("got a Double: " + f)
    case other => println("got something else: " + other)
  }
}

// Matching on sequences
val willWork = List(1, 3, 23, 90)
val willNotWork = List(4, 18, 52)
val empty = List()

for (l <- List(willWork, willNotWork, empty)) {
  l match {
    case List(_, 3, _, _) => println("Four elements, with the 2nd being '3'.")
    case List(_*) => println("Any other list with 0 or more elements.")
  }
}

def processList(l: List[Any]): Unit = l match {
  case head :: tail =>
    print("Head: " +  head + ", ")
    processList(tail)
  case Nil => println("")
}

def listAnalysis(list: List[Any]) = list match {
  case Nil => "empty"
  // cons operator, extracts head and tail and match head to 'a'
  case 'a' :: tail => "starting by 'a'"
  // type match the first element of the list and check that we have an integer
  // and if head is > 3
  case (head:Int) :: _ if head > 3 => "starting by an int greater than 3"
  // head is matched with Int type and tail can be anything
  case (head:Int) :: _ => "starting by an int"
  case _ => "whatever"
}

listAnalysis(List())                             //> java.lang.String = empty
listAnalysis("This is a test".toList)            //> java.lang.String = whatever
listAnalysis("abcde".toList)                     //> java.lang.String = starting by 'a'
listAnalysis(List(1,2,3))                        //> java.lang.String = starting by an int
listAnalysis(List(42,24,36))                     //> java.lang.String = starting by an int greater than 3
listAnalysis("a".toList)                         //> java.lang.String = starting by 'a'

for (l <- List(willWork, willNotWork, empty)) {
  print("List: ")
  processList(l)
}

// Match on Tuples
val tupA = ("Good", "Morning!")
val tupB = ("Guten", "Tag!")
for (tup <- List(tupA, tupB)) {
  tup match {
    case (thingOne, thingTwo) if thingOne == "Good" =>
      println("A two-tuple starting with 'Good'.")
    case (thingOne, thingTwo) => println("This has two things: " + thingOne + " and " + thingTwo)
  }
}

def doubleMatch(foo: Any, bar: Any) = (foo, bar) match {
  case ('a', 'b') => "a and b"
  case (1, 'b') => "1 and b"
  case (1, _) => "1 and "+ bar
  case (a:Float, _) => "foo float"
  case _ => "unknown case"
}

doubleMatch(1, "test")                           //> java.lang.String = 1 and test
doubleMatch(1, 'b')                              //> java.lang.String = 1 and b
doubleMatch(42, Nil)                             //> java.lang.String = unknown case
doubleMatch('a', 'b')                            //> java.lang.String = a and b
doubleMatch(4.2f, 42)                            //> java.lang.String = foo float

// Case Classes
case class Person(name: String, age: Int)
val alice = new Person("Alice", 25)
val bob = new Person("Bob", 32)
val charlie = new Person("Charlie", 32)
for (person <- List(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25) => println("Hi Alice!")
    case Person("Bob", 32) => println("Hi Bob!")
    case Person(name, age) =>
      println("Who are you, " + age + " year-old person named " + name + "?")
  }
}

// RegEx
val BookExtractorRE = """Book: title=([^,]+),\s+authors=(.+)""".r
val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r
val catalog = List(
  "Book: title=Programming Scala, authors=Dean Wampler, Alex Payne",
  "Magazine: title=The New Yorker, issue=January 2009",
  "Book: title=War and Peace, authors=Leo Tolstoy",
  "Magazine: title=The Atlantic, issue=February 2009",
  "BadData: text=Who put this here??"
)
for (item <- catalog) {
  item match {
    case BookExtractorRE(title, authors) =>
      println("Book \"" + title + "\", written by " + authors)
    case MagazineExtractorRE(title, issue) =>
      println("Magazine \"" + title + "\", issue " + issue)
    case entry => println("Unrecognized entry: " + entry)
  }
}

// Binding nested variables in Scala
class Role
case object Manager extends Role
case object Developer extends Role
case class Person1(name: String, age: Int, role: Role)

val alice1 = new Person1("Alice1", 25, Developer)
val bob1 = new Person1("Bob1", 32, Manager)
val charlie1 = new Person1("Charlie1", 32, Developer)
// p @ Persion1 notation
// matching on particular kinds of Person objects inside the enclosing tuple.
// We also want to assign the Person to a variable p, so we can use it for processing
// p @ Person(...) syntax gives us a way to flatten this nesting of match
// statements into one statement
for (item <- Map(1 -> alice1, 2 -> bob1, 3 -> charlie1)) {
  item match {
    case (id, p @ Person1(_, _, Manager)) => "%s is overpaid.\n".format(p)
    case (id, p @ Person1(_, _, _)) => "%s is underpaid.\n".format(p)
  }
}



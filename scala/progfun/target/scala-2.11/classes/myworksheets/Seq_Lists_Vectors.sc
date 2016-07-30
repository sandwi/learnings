//
// Sequence
// Scala Seq is a trait that defines a List
// Sequences always have a defined order of elements. Sequences provide a method apply for indexing.
// Ordering is maintained.
// Scala List is a LinkedList, a type of Seq, and is implemented with Cons functional construct
// List is an implementation of Seq.
val seq = Seq(1, 1, 2)
// Check seq is a List
val fruits = List("Apple", "Orange", "Mango")
val nums = List(1, 2, 3, 5)
val empty = List()
val diag3 = List(List(1,0,0), List(0, 1, 0), List(0,0,1))
// Traversing List with a closure
List(1, 2, 3, 4, 5) foreach { i => println("Int: " + i) }
// For Comprehension
val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
  "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
for (breed <- dogBreeds)
  println(breed)
for (breed <- dogBreeds
     if breed.contains("Terrier")
) println(breed)
for (breed <- dogBreeds
     if breed.contains("Terrier");
     if !breed.startsWith("Yorkshire")
) println(breed)
// The yield keyword is used to generating new collections with for expressions.
val filteredBreeds = for {
  breed <- dogBreeds
  if breed.contains("Terrier")
  if !breed.startsWith("Yorkshire")
} yield breed

// define variables inside the first part of the
// for expressions that can be used in the latter part
for {
  breed <- dogBreeds
  upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)

// Generator Expressions
for (i <- 1 to 10) println(i)
for (i <- 0 until 10) println(i)
// Prepending List
val list1 = List("Programming", "Scala")
val list2 = "People" :: "should" :: "read" :: list1
println(list2)
// use of flatMap
// faltens a list of lists
val graph = List(
  "a", List("b1", "b2", "b3"), List("c1", List("c21", Nil, "c22"), Nil, "e")
)
def flatten(list: List[_]): List[_] = list flatMap {
  case head :: tail => head :: flatten(tail) // non-empty list
  case Nil => Nil
  case x => List(x)
}
println(flatten(graph))
// Reducing & Folding
// Folding: Folding starts with an initial “seed” value and processes each element in the context of
// that value.
// Reducing: reducing doesn’t start with a user-supplied initial value.
// Rather,it uses the first element as the initial value
// Reduction - start with 1st element and adds successive values
List(1,2,3,4,5,6) reduceLeft(_ + _)
//Folding, starts with initial value of 10 and  multiplies each successive value
List(1,2,3,4,5,6).foldLeft(10)(_ * _)
// Folding with
List(1, 2, 3, 4, 5, 6).foldLeft(List[String]()) {
  (list, x) => ("<" + x + ">") :: list
}.reverse
List(1, 2, 3, 4, 5, 6).foldRight(List[String]()) {
  (x, list) => ("<" + x + ">") :: list
}


// Merge Sort
def msort(xs: List[Int]) : List[Int] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[Int], ys: List[Int]) : List[Int] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (x < y) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}
val nums1 = List(4, -2, -9, 5, 9, 20)
msort(nums1)

// Map and filters on Lists - Higher Order Functions
nums1 filter(x => x > 2)
nums1 filterNot(x => x > 2)
nums1 partition(x => x > 2)
nums1 takeWhile(x => x > 2)
nums1 dropWhile(x => x > 2)

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

val data = List("a", "a", "a", "c", "d", "d", "d", "a")
pack(data)

def encode[T](xs: List[T]): List[(T, Int)] = {
  pack(xs) map (ys => (ys.head, ys.length))
}

encode(data)

// Vector is a Seq
// log32(N) access performance as opposed to N performance of list on access
val people = Vector("Kennedy", "Reagon", "Roosevelt")
val p1 = Vector("Clinton", "Bush")
// append to p1
val p2 = people +: p1
// prepend to p1
val p3 = p1 :+ people

// Ranges
// exclusive
val r: Range = 1 until 5
// inclusive
val r2: Range = 1 to 5
// by step
val r3: Range = 1 to 10 by 3
val r4: Range = 6 to 1 by -2

def isPrime(n: Int): Boolean = (2 until n) forall ( d => n % d != 0 )
isPrime(2)
isPrime(10)
isPrime(11)
//
val n = 7
(1 until n) map ( i => (1 until i) map ( j => (i, j)))






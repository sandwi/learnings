// Arrays are mutable data structure in Scala
// Array in Scala can be generic
// Scala arrays are compatible with Scala sequences - you can pass an Array[T] where a Seq[T] is required.
// Finally, Scala arrays also support all sequence operations.
val a: Array[String] = new Array(5)
a(0) = "Entry1"
a(1) = "Entry2"
a(2) = "Entry3"
println(a(1))
val a1 = Array(1, 2, 3)
val a2 = a1 map (_ * 3)
val a3 = a2 filter (_ % 2 != 0)
a3.reverse

// generic array creation demands class manifests.
// So whenever creating an array of a type parameter T, you also need to provide
// an implicit class manifest (scala.reflect.ClassTag) for T.
//
// Scala compiler requires class manifest to be provided in generic Array uses.
// The Scala compiler will construct class manifests automatically if you instruct
// it to do so. “Instructing” means that you demand a class manifest as an implicit
// parameter, like this:
def evenElems[T: scala.reflect.ClassTag](xs: Vector[T]): Array[T] = {
  val arr = new Array[T]((xs.length + 1) / 2)
  for (i <- 0 until xs.length by 2)
    arr(i / 2) = xs(i)
  arr
}
evenElems(Vector(1, 2, 3, 4, 5))
evenElems(Vector("this", "is", "a", "test", "run"))


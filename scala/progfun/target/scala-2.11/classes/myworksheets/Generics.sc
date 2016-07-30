// A Generic Merge Sort
def msort[T](xs: List[T])(lt: (T, T) => Boolean) : List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]) : List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (lt(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val (fst, snd) = xs splitAt n
    merge(msort(fst)(lt), msort(snd)(lt))
  }
}
val nums = List(4, -2, -9, 5, 9, 20)
//types are infered based on nums
msort(nums)((x,y) => x < y)
val fruits = List("apple", "banana", "avacado")
msort(fruits)((x, y) => x.compareTo(y) < 0)

//  Using Ordering
import math.Ordering
def msort2[T](xs: List[T])(ord: Ordering[T]) : List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]) : List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (ord.lt(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val (fst, snd) = xs splitAt n
    merge(msort2(fst)(ord), msort2(snd)(ord))
  }
}

//types are infered based on nums
msort2(nums)(Ordering.Int)
msort2(fruits)(Ordering.String)
// Implicit parameter to a function
def msort3[T](xs: List[T])(implicit ord: Ordering[T]) : List[T] = {
  val n = xs.length/2
  if (n == 0) xs
  else {
    def merge(xs: List[T], ys: List[T]) : List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (ord.lt(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val (fst, snd) = xs splitAt n
    merge(msort3(fst), msort3(snd))
  }
}
//types are infered based on nums
msort3(nums)
msort3(fruits)

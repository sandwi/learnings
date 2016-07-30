// Given a positive integer n, find all pairs of positive integers i, j
// such that 1 <= j < i < n, i+j is prime.
def isPrime(n: Int): Boolean = (2 until n) forall ( d => n % d != 0 )
isPrime(2)
isPrime(10)
isPrime(11)
// Step 1, generate Pairs (i,j)
val n = 7
((1 until n) map ( i => (1 until i) map ( j => (i, j)))).flatten

(1 until n) flatMap( i =>
  (1 until i) map ( j => (i, j)) ) filter ( pair =>
    isPrime(pair._1 + pair._2) )
// Solution with Scala's For-Expression
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)

// Scalar Product
def scalarProduct(xs: List[Double], ys: List[Double]) =
  (for((x,y) <- xs zip ys) yield x * y).sum


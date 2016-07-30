// Higher Order functions
// sum of a function over interval a,b
// function can be integer, square, cubes etc.
// uses anonymous functions
def sum(f: Int => Int, a: Int, b: Int) = {
  def loop(a: Int, acc: Int): Int =
    if (a > b) acc
    else loop(a + 1, f(a) + acc)
  loop(a, 0)
}
sum(x => x * x, 3, 4)

// Currying
// Generic product function over an interval (a, b)
def product(f: Int => Int)(a: Int, b: Int): Int = {
  if (a > b) 1
  else f(a) * product(f)(a + 1, b)
}
// Sum of squares
product(x => x * x)(3,4)
// Factorial using Currying
def fact(n:Int) = product(x => x)(1, n)
fact(5)

// Generic sum & product over an interval
// Map Reduce
def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a: Int, b: Int): Int =
  if (a > b) zero
  else combine(f(a), mapReduce(f, combine, zero)(a+1, b))
def product2(f: Int => Int)(a: Int, b: Int): Int =
  mapReduce(f, (x, y) => x * y, 1)(a, b)
def fact2(n: Int) = product2(x => x)(1, n)
fact2(5)

// Fixed Point algorithm
// f(x) = x, for some x, x is called a fixed point
val tolerance = 0.0001
def isCloseEnough(x: Double, y: Double) =
  math.abs((x - y) / x) / x < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  def iterate(guess: Double): Double = {
    val next = f(guess)
    if (isCloseEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}
fixedPoint(x => 1 + x/2)(1)
def averageDamp(f: Double => Double)(x: Double) = (x + f(x))/2

//Square Root
def sqrt(x: Double) =
  fixedPoint(y => (y + x/y) / 2)(1.0)

def sqrt2(x:Double) =
  fixedPoint(averageDamp(y => x/y))(1.0)
sqrt(25)
sqrt2(25)
//Rationals
// Do some rational operations
val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x.numer
x.denom
x.add(y).add(z)
x.sub(y).sub(z)
y.add(y)
x.less(y)
x.max(y)

// Rational Class
class Rational(x: Int, y: Int) {
  require (y != 0, "denominator must be nonzero")

  def this(x: Int) = this(x, 1)

  private def gcd(a: Int, b:Int): Int =
    if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)

  def numer = x / g
  def denom = y / g


  def add(r: Rational) =
    new Rational(
      numer * r.denom + r.numer * denom,
      denom * r.denom)
  def + (r: Rational) = add(r)

  def neg: Rational = new Rational(-numer, denom)
  def unary_- : Rational = new Rational(-numer, denom)

  def sub(that: Rational) = add(that.neg)
  def - (that: Rational) = this + -that

  def less(that: Rational) = numer * that.denom < that.numer * denom
  def < (that: Rational) = less(that)

  def max(that: Rational) = if (this.less(that)) that else this

  override def toString = numer + "/" + denom
}



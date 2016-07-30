// factorial with tail recursion
def factorial(n:Int): Int = {
  def loop(acc:Int, n:Int):Int =
    if (n == 0) acc
    else loop(acc * n, n - 1)
  loop(1, n)
}

factorial(4)

// Square using Newton's Method
def sqrt(x: Double) = {
  def sqrtIter(guess: Double): Double = {
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))
  }

  def isGoodEnough(guess: Double) = {
    Math.abs(guess * guess - x) / x < 0.001
  }

  def improve(guess: Double) = {
    (guess + x / guess) / 2
  }

  sqrtIter(1.0)
}

sqrt(2)
sqrt(4)
sqrt(1e-6)
sqrt(1e+60)

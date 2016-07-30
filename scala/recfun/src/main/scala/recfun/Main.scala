package recfun

import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    println("Balances?:" + balance("(just an) example".toList))

    println("Change Count: " + countChange(10, List(1,2)))
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
        if (c == 0) 1
        else if (c == r) 1
        else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def loop(chars: List[Char], acc: Int): Boolean = {
        if (chars.isEmpty) {
          acc == 0
        } else {
          val h = chars.head
          val n =
            if (h == '(') acc + 1
            else if (h == ')') acc - 1
            else acc
          if (n >= 0) loop(chars.tail, n)
          else false
        }
      }

      loop(chars, 0)

  }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def count(m: Int, c: List[Int]) : Int = {
        if (c.isEmpty) 0
        else if (m - c.head == 0) 1
        else if (m - c.head < 0) 0
        else countChange(m - c.head, c) + countChange(m, c.tail)
      }
      count(money, coins.sorted)
  }
}

// Sets
// A set is a collection that contains no duplicate elements. No ordering.
// Fundamental operation on Set is contains.
// Fundamental operation on Set is head & tail.
// Fundamental operation on Vector is indexing
val set = Set(1, 1, 2)

// N queen problem
// n is the chess board size i.e. n X n board
def queens(n: Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] = {
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if (isSafe(col, queens))
      } yield col::queens
    }
  placeQueens(n)
}

def isSafe(col: Int, queens: List[Int]): Boolean = {
  val rows = queens.length
  val queensWithRow = (rows - 1 to 0 by -1) zip queens
  queensWithRow forall {
    case (r, c) => col != c && Math.abs(col - c) != rows - r
  }
}

def show(queens: List[Int]) = {
  val lines =
    for (col <- queens.reverse)
      yield Vector.fill(queens.length)("*  ").updated(col, "X  ").mkString

  "\n" + (lines mkString "\n")
}

queens(4)

(queens(4) map show) mkString "\n"



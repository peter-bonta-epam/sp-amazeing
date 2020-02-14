package example

class Maze(val width: Int, val height: Int) {
  import Maze._

  val grid: Array[Array[Tile]] = Array.fill(height, width)(Wall)

  def build(): Unit  = {
    if (notValidMaze(width, height)) println("Invalid maze! Please go outside and hurt yourself. Thank you.")
    else {
      initGrid()
      createPath(Coordinate(1, 1))
    }
  }

  def reveal(): Unit = {
    print("\u001b[2J")
    Thread.sleep(2000)
    println("You are A-MAZE-ING! Did you know that?")
    Thread.sleep(5000)
    println("One moment, generating your maze....")
    Thread.sleep(2000)
    println("Still working. Have a coffee until I'm finished.")
    Thread.sleep(7000)
    grid.foreach(row => {
      row.foreach(print)
      println
    })
  }

  private def createPath(currentCoordinate: Coordinate): Unit = {
    def getNeighbour(direction: Direction, coordinate: Coordinate): Coordinate = coordinate + direction
    var directions = scala.util.Random.shuffle(Set(Directions.North, Directions.East, Directions.South, Directions.West))
    while (directions.nonEmpty) {
      val direction = directions.head
      val coordinate1 = getNeighbour(direction, currentCoordinate)
      val coordinate2 = getNeighbour(direction, coordinate1)
      if (inBoundaries(coordinate2)) {
        if(isWall(coordinate1) && isWall(coordinate2)) {
          makePath(coordinate1)
          makePath(coordinate2)
          createPath(coordinate2)
        }
      }
      directions = directions - direction
    }
  }

  private def initGrid(): Unit = {
    makePath(Coordinate(1, 1))
    makePath(Coordinate(0, 1))
    makePath(Coordinate(height -1, width -2))
  }

  private def makePath(coordinate: Coordinate): Unit = {
    grid(coordinate.y)(coordinate.x) = Path
  }

  private def isWall(coordinate: Coordinate): Boolean = grid(coordinate.y)(coordinate.x) == Wall

  private def inBoundaries(coordinate: Coordinate): Boolean = {
    (coordinate.x > 0 && coordinate.x < width) && (coordinate.y > 0 && coordinate.y < height)
  }
}

object Maze {
  val MinWidth, MinHeight = 5
  val MaxWidth, MaxHeight = 1001

  def notValidMaze(width: Int, height: Int): Boolean = {
    if (width != height) true
    else {
      val widthIsOK = width % 2 != 0 && (width >= MinWidth && width <= MaxWidth)
      val heightIsOK = height % 2 != 0 && (height >= MinHeight && height <= MaxHeight)
      !widthIsOK || !heightIsOK
    }
  }
}

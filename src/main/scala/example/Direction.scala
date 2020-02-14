package example

case class Direction(dx: Int, dy: Int)

object Directions {
  val North = Direction(0, -1)
  val South = Direction(0, 1)
  val East = Direction(1, 0)
  val West = Direction(-1, 0)
}
package example

case class Coordinate(x: Int, y: Int) {
  def +(that: Direction): Coordinate = Coordinate(this.x + that.dx, this.y + that.dy)
}

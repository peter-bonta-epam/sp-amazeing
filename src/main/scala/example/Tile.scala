package example

sealed trait Tile

case object Wall extends Tile {
  override def toString: String = "[]"
}
case object Path extends Tile {
  override def toString: String = "  "
}

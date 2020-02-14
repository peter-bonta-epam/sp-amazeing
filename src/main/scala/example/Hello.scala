package example

object Hello extends App {
  val maze = new Maze(499, 499)
  maze.build()
  maze.reveal()
}

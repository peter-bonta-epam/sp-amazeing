package example

object Hello extends App {
  val maze = new Maze(101, 101)
  maze.build()
  maze.reveal()
}

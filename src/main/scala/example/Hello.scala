package example

object Hello extends App {
  val maze = new Maze(Maze.MaxWidth, Maze.MaxHeight)
  maze.build()
  maze.reveal()
}

package at.jku.ctc.entity

data class ShortestPath(override val start: Direction, val totalLength: Int) : Path(start)
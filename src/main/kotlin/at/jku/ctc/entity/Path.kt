package at.jku.ctc.entity

open class Path(open val start: Direction, var end: Direction = start) : Iterable<Direction> {

    override fun iterator(): Iterator<Direction> {
        return start
    }

    fun addDirection(street: Street) {
        this.end.direction = Direction(street)
        this.end = checkNotNull(this.end.direction)
    }
}
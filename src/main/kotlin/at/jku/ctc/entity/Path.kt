package at.jku.ctc.entity

open class Path(open val start: Direction, var end: Direction = start) : Iterable<Direction> {

    override fun iterator(): Iterator<Direction> {
        return start
    }

    open fun addDirection(street: Street) {
        this.end.direction = Direction(street)
        this.end = checkNotNull(this.end.direction)
    }

    open fun removeEnd() {
        this.forEach {
            it.takeIf { direction -> direction.next() == end }?.apply {
                it.direction = null
                end = it
            }
        }
    }
}
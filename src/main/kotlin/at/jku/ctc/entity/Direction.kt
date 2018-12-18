package at.jku.ctc.entity

data class Direction(val street: Street, var direction: Direction? = null) : Iterator<Direction> {

    override fun next(): Direction {
        return checkNotNull(direction)
    }

    override fun hasNext(): Boolean {
        return direction != null
    }
}
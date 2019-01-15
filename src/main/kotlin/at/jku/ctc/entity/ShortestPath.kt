package at.jku.ctc.entity

data class ShortestPath(override val start: Direction, override var end: Direction = start,
                        var totalLength: Int = 0) : Path(start) {
    fun addDirection(street: Street) {
        super.addDirection(Direction(street = street))
        totalLength += street.length
    }

    override fun removeEnd() {
        totalLength -= end.street.length
        super.removeEnd()
    }
}
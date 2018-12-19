package at.jku.ctc.entity

data class ShortestPath(override val start: Direction, var totalLength: Int = start.street.length) : Path(start) {
    override fun addDirection(street: Street) {
        super.addDirection(street)
        totalLength += street.length
    }

    override fun removeEnd() {
        totalLength -= end.street.length
        super.removeEnd()
    }
}
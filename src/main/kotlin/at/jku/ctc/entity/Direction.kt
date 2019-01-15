package at.jku.ctc.entity

import javax.persistence.*

@Entity
data class Direction(@field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1,
                     @field:ManyToOne val street: Street = Street(),
                     @field:ManyToOne var direction: Direction? = null) :
        Iterator<Direction> {

    override fun next(): Direction {
        return checkNotNull(direction)
    }

    override fun hasNext(): Boolean {
        return direction != null
    }

    fun deepCopy(): Direction {
        val copy = Direction(street = street)
        copy.direction = this.direction?.deepCopy()
        return copy
    }

    fun containsStreet(street: Street): Boolean {
        if (this.street == street) {
            return true
        }
        var temp = this
        while (temp.hasNext()) {
            temp = temp.next()
            if (temp.street == street) {
                return true
            }
        }
        return false
    }
}
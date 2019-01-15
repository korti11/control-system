package at.jku.ctc.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(
    NamedQuery(name = "BlockedPath.GetAll", query = "select p from BlockedPath p"),
    NamedQuery(name = "BlockedPath.Size", query = "select count(p) from BlockedPath p")
)
open class BlockedPath(@field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1,
                       @field:ManyToOne override val start: Direction = Direction(),
                       val blockadeStart: LocalDateTime = LocalDateTime.MIN,
                       val blockadeType: BlockadeType = BlockadeType.Maintenance,
                       val priorityToAvoid: PriorityType = PriorityType.Lowest) : Path(start) {

    fun containsPath(path: Path): Boolean {
        var pathStart = path.start
        if(start.containsStreet(pathStart.street)) {
            return true
        }
        while (pathStart.hasNext()) {
            pathStart = pathStart.next()
            if(start.containsStreet(pathStart.street)) {
                return true
            }
        }
        return false
    }

}
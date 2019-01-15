package at.jku.ctc.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
open class BlockedPath(@field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = -1,
                       @field:ManyToOne override val start: Direction = Direction(),
                       val blockadeStart: LocalDateTime = LocalDateTime.MIN,
                       val blockadeType: BlockadeType = BlockadeType.Maintenance,
                       val priorityToAvoid: PriorityType = PriorityType.Lowest) : Path(start)
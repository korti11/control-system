package at.jku.ctc.entity

import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
class MaintenancePath(id: Long = -1, start: Direction = Direction(),
                      blockadeStart: LocalDateTime = LocalDateTime.MIN,
                      val maintenanceFinish: LocalDateTime = LocalDateTime.MIN,
                      val maintenanceFinished: Boolean = false) :
        BlockedPath(id, start, blockadeStart, BlockadeType.Maintenance, PriorityType.Highest)
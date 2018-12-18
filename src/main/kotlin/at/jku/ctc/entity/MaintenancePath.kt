package at.jku.ctc.entity

import java.time.LocalDateTime

data class MaintenancePath(override val start: Direction, override val blockadeStart: LocalDateTime,
                           val maintenanceFinish: LocalDateTime,
                           override val priorityToAvoid: PriorityType = PriorityType.Highest,
                           val maintenanceFinished: Boolean = false) :
        BlockedPath(start, blockadeStart, BlockadeType.Maintenance, priorityToAvoid)
package at.jku.ctc.entity

import java.time.LocalDateTime

data class MaintenancePath(override val start: Direction, override val blockadeStart: LocalDateTime,
                           override val priorityToAvoid: PriorityType, val maintenanceFinish: LocalDateTime,
                           val maintenanceFinished: Boolean) :
        BlockedPath(start, blockadeStart, BlockadeType.Maintenance, priorityToAvoid)
package at.jku.ctc.entity

import java.util.*

data class MaintenancePath(override val start: Direction, override val blockadeStart: Date,
                           override val priorityToAvoid: PriorityType, val maintenanceFinish: Date,
                           val maintenanceFinished: Boolean) :
        BlockedPath(start, blockadeStart, BlockadeType.Maintenance, priorityToAvoid)
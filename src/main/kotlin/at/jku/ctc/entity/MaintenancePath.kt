package at.jku.ctc.entity

import java.util.*

data class MaintenancePath(override val start: Direction, override val blockadeStart: Date,
                           val maintenanceFinish: Date, val maintenanceFinished: Boolean) :
        BlockedPath(start, blockadeStart)
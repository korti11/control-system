package at.jku.ctc.entity

import java.time.LocalDateTime

open class BlockedPath(start: Direction, open val blockadeStart: LocalDateTime, val blockadeType: BlockadeType,
                       open val priorityToAvoid: PriorityType) : Path(start)
package at.jku.ctc.entity

import java.util.*

open class BlockedPath(start: Direction, open val blockadeStart: Date) : Path(start)
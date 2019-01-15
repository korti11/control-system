package at.jku.ctc.algorithm

import at.jku.ctc.entity.PriorityType
import at.jku.ctc.entity.ShortestPath
import at.jku.ctc.entity.Street

interface IShortestPath {

    fun findShortestPath(startStreet: Street, endStreet: Street, avoidPaths: Boolean = false,
                         priorityType: PriorityType = PriorityType.Normal): ShortestPath

}
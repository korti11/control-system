package at.jku.ctc.algorithm

import at.jku.ctc.entity.Address
import at.jku.ctc.entity.PriorityType
import at.jku.ctc.entity.ShortestPath

interface IShortestPath {

    fun findShortestPath(startAddress: Address, endAddress: Address, priorityType: PriorityType = PriorityType.Normal):
            ShortestPath

}
package at.jku.ctc.algorithm

import at.jku.ctc.business.StreetMap
import at.jku.ctc.entity.*
import javax.enterprise.context.RequestScoped
import javax.inject.Inject

@RequestScoped
open class Dijkstra : IShortestPath {

    @Inject
    private lateinit var streetMap: StreetMap

    private val neighbors = mutableListOf<ShortestPath>()
    private val seekedStreets = mutableMapOf<Street, ShortestPath>()

    override fun findShortestPath(startAddress: Address, endAddress: Address, priorityType: PriorityType):
            ShortestPath {
        var currentNode = ShortestPath(Direction(street = startAddress.street))
        while (currentNode.end.street != endAddress.street) {
            seekedStreets[currentNode.end.street] = currentNode
            val neighbors = streetMap.getStreetNeighbors(currentNode.end.street)
            for(neighbor in neighbors) {
                if(!seekedStreets.containsKey(neighbor)) {
                    val newPath = currentNode.copy()
                    newPath.addDirection(neighbor)
                    this.neighbors.add(newPath)
                }
            }
            currentNode = checkNotNull(this.neighbors.minBy { it.totalLength })
            this.neighbors.remove(currentNode)
        }
        return currentNode
    }

}
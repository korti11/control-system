package at.jku.ctc.mock

import at.jku.ctc.algorithm.Dijkstra
import at.jku.ctc.business.StreetMap
import at.jku.ctc.entity.*
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class DijkstraMockTest {

    private val street1 = Street(name = "Stadlberg", length = 15)
    private val street2 = Street(name = "Schulstraße", length = 10)
    private val street3 = Street(name = "Schmidberg", length = 50)
    private val street4 = Street(name = "Wolferner Straße", length = 20)
    private val street5 = Street(name = "Haberfellnerberg", length = 17)
    private val street6 = Street(name = "Niederneukirchen Bezirrksstraße", length = 5)
    private val street7 = Street(name = "Mitterberg", length = 25)
    private val street8 = Street(name = "Bachbinderberg", length = 25)

    private val streetNeighbor1 = arrayOf(street2, street3)
    private val streetNeighbor2 = arrayOf(street1, street3)
    private val streetNeighbor3 = arrayOf(street1, street2, street4, street5, street6)
    private val streetNeighbor4 = arrayOf(street3, street5)
    private val streetNeighbor5 = arrayOf(street4, street3)
    private val streetNeighbor6 = arrayOf(street2, street7)
    private val streetNeighbor7 = arrayOf(street6, street8)
    private val streetNeighbor8 = arrayOf(street7, street5)

    private val blockedPath = BlockedPath(start = Direction(street = street6), blockadeType = BlockadeType.Emergency,
            priorityToAvoid = PriorityType.High)

    private val streetMap: StreetMap = Mockito.mock(StreetMap::class.java)

    init {
        Mockito.`when`(streetMap.getStreetNeighbors(street1)).thenReturn(streetNeighbor1)
        Mockito.`when`(streetMap.getStreetNeighbors(street2)).thenReturn(streetNeighbor2)
        Mockito.`when`(streetMap.getStreetNeighbors(street3)).thenReturn(streetNeighbor3)
        Mockito.`when`(streetMap.getStreetNeighbors(street4)).thenReturn(streetNeighbor4)
        Mockito.`when`(streetMap.getStreetNeighbors(street5)).thenReturn(streetNeighbor5)
        Mockito.`when`(streetMap.getStreetNeighbors(street6)).thenReturn(streetNeighbor6)
        Mockito.`when`(streetMap.getStreetNeighbors(street7)).thenReturn(streetNeighbor7)
        Mockito.`when`(streetMap.getStreetNeighbors(street8)).thenReturn(streetNeighbor8)
    }

    private fun injectStreetMap(shortestPath: Dijkstra) {
        val clazz = shortestPath::class.java
        val field = clazz.getDeclaredField("streetMap")
        field.isAccessible = true
        field.set(shortestPath, streetMap)
    }

    @Test
    fun t01FindShortestPath() {
        val dijkstra = Dijkstra()
        injectStreetMap(dijkstra)
        val path = dijkstra.findShortestPath(street1, street4)
        Assert.assertEquals("Start street should be \"Stadlberg\"", path.start.street, street1)
        Assert.assertEquals("End street should be \"Wolferner Straße\"", path.end.street, street4)
        Assert.assertEquals("Total length is not correct.", 70, path.totalLength)
    }

}
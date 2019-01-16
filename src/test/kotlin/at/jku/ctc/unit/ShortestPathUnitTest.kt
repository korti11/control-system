package at.jku.ctc.unit

import at.jku.ctc.entity.Direction
import at.jku.ctc.entity.ShortestPath
import at.jku.ctc.entity.Street
import org.junit.Assert
import org.junit.Test

class ShortestPathUnitTest {

    private val street1 = Street(name = "Stadlberg", length = 15)
    private val street2 = Street(name = "Schulstraße", length = 10)
    private val street3 = Street(name = "Schmidberg", length = 50)

    @Test
    fun t01RightTotalLength() {
        val shortestPath = ShortestPath(Direction(street = street1))
        shortestPath.addDirection(street2)
        shortestPath.addDirection(street3)
        Assert.assertEquals("Total length should be 60", 60, shortestPath.totalLength)
    }
}
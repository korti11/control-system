package at.jku.ctc.unit

import at.jku.ctc.entity.Direction
import at.jku.ctc.entity.Path
import at.jku.ctc.entity.Street
import org.junit.Assert
import org.junit.Test

class PathUnitTest {

    private val street1 = Street(name = "Stadlberg", length = 15)
    private val street2 = Street(name = "Schulstraße", length = 10)
    private val street3 = Street(name = "Schmidberg", length = 50)

    @Test
    fun t01CreatePath() {
        val path = Path(Direction(street = street1))
        Assert.assertEquals("Start street should equal to \"Stadlberg\"", street1, path.start.street)
        Assert.assertEquals("End street should equal to \"Stadlberg\"", street1, path.end.street)
    }

    @Test
    fun t02AddOneDirection() {
        val path = Path(Direction(street = street1))
        path.addDirection(Direction(street = street2))
        Assert.assertEquals("Start street should equal to \"Stadlberg\"", street1, path.start.street)
        Assert.assertEquals("End street should equal to \"Schulstraße\"", street2, path.end.street)
    }

    @Test
    fun t03AddMultipleDirections() {
        val path = Path(Direction(street = street1))
        path.addDirection(Direction(street = street2))
        path.addDirection(Direction(street = street3))
        Assert.assertEquals("Start street should equal to \"Stadlberg\"", street1, path.start.street)
        Assert.assertEquals("Second direction should equal to \"Schulstraße\"", street2, path.start.direction?.street)
        Assert.assertEquals("Third direction should equal to the end of the path", path.end,
                path.start.direction?.direction)
        Assert.assertEquals("End street should equal to \"Schmidberg\"", street3, path.end.street)
    }

}
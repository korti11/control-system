package at.jku.ctc.unit

import at.jku.ctc.entity.Direction
import at.jku.ctc.entity.Street
import org.junit.Assert
import org.junit.Test

class DirectionUnitTest {

    private val street1 = Street(name = "Stadlberg", length = 15)
    private val street2 = Street(name = "Schulstraße", length = 10)
    private val street3 = Street(name = "Schmidberg", length = 50)

    @Test
    fun t01CreateDirection() {
        val direction = Direction(street = street1)
        Assert.assertEquals("Street should equal to \"Stadlberg\"", street1, direction.street)
        Assert.assertNull("Next direction should be null", direction.direction)
    }

    @Test
    fun t02ContainsStreet() {
        val direction = Direction(street = street1)
        direction.direction = Direction(street = street2)
        direction.direction?.direction = Direction(street = street3)
        Assert.assertTrue("Direction should contain street \"Schmidberg\"", direction.containsStreet(street3))
    }

}
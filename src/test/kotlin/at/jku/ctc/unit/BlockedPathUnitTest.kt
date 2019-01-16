package at.jku.ctc.unit

import at.jku.ctc.entity.BlockedPath
import at.jku.ctc.entity.Direction
import at.jku.ctc.entity.Path
import at.jku.ctc.entity.Street
import org.junit.Assert
import org.junit.Test

class BlockedPathUnitTest {

    private val street1 = Street(name = "Stadlberg", length = 15)
    private val street2 = Street(name = "Schulstraße", length = 10)
    private val street3 = Street(name = "Schmidberg", length = 50)

    @Test
    fun t01ContainsPath() {
        val blockedPath = BlockedPath(start = Direction(street = street1))
        blockedPath.addDirection(Direction(street = street2))
        blockedPath.addDirection(Direction(street = street3))
        val path = Path(Direction(street = street2))
        path.addDirection(Direction(street = street3))
        Assert.assertTrue("Path should be contained in the blocked path", blockedPath.containsPath(path))
    }
}
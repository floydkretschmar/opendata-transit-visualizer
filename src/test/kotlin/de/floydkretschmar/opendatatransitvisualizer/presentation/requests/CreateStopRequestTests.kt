package de.floydkretschmar.opendatatransitvisualizer.presentation.requests

import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationCoordinates
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class CreateStopRequestTests {

    @Test
    fun toCommand_whenCalled_returnCreateStopCommand() {
        val createStopRequest = CreateStopRequest("stopIdentifier", "name", "code", LocationCoordinates(1.0, 1.0), LocationType.STATION, UUID.randomUUID())

        val command = createStopRequest.toCommand()

        assertThat(command.stopIdentifier).isEqualTo(createStopRequest.stopIdentifier)
        assertThat(command.name).isEqualTo(createStopRequest.name)
        assertThat(command.code).isEqualTo(createStopRequest.code)
        assertThat(command.locationCoordinates).isEqualTo(createStopRequest.locationCoordinates)
        assertThat(command.locationType).isEqualTo(createStopRequest.locationType)
        assertThat(command.parentId).isEqualTo(createStopRequest.parentId)
    }
}
package de.floydkretschmar.opendatatransitvisualizer.presentation.controllers

import de.floydkretschmar.opendatatransitvisualizer.application.stops.commands.CreateStop
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationCoordinates
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import de.floydkretschmar.opendatatransitvisualizer.presentation.requests.CreateStopRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class StopControllerTests {
    @Test
    fun createStop_whenCalled_executesCreateStopCommand() {
        val stop = Stop.Builder()
            .name("stopName")
            .locationCoordinates(LocationCoordinates(1.0, 1.0))
            .build("stopIdentifier")
        val mockCommand = mock<CreateStop> {
            on { execute(any())}.doReturn(stop)
        }
        val mockRequest = mock<CreateStopRequest> {
            on { toCommand() }.doReturn(mockCommand)
        }
        val controller = StopController(mock())

        val result = controller.createStop(mockRequest)

        verify(mockRequest).toCommand()
        verify(mockCommand).execute(any())
        assertThat(result).isEqualTo(stop)
    }
}
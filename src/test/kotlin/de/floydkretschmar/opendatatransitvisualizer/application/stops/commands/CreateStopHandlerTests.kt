package de.floydkretschmar.opendatatransitvisualizer.application.stops.commands

import de.floydkretschmar.opendatatransitvisualizer.application.abstractions.repositories.StopRepository
import de.floydkretschmar.opendatatransitvisualizer.application.exceptions.EntityNotFoundException
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationCoordinates
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationType
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.invocation.InvocationOnMock
import org.mockito.kotlin.*
import java.util.*

class CreateStopHandlerTests {
    var parent: Stop? = null

    @BeforeEach
    fun setup() {
        parent = Stop.Builder().name("Parent").locationType(LocationType.STOP).locationCoordinates(LocationCoordinates(1.0, 1.0)).build("parentIdentifier")
    }

    @Test
    fun handle_whenCalledWithoutParentId_createNewStop() {
        val generatedId = UUID.randomUUID()
        val repositoryMock = mock<StopRepository> {
            onGeneric { save(any()) }.doAnswer { invocation: InvocationOnMock ->
                val stop = invocation.getArgument<Stop>(0)
                stop.id = generatedId
                stop
            }
        }
        val handler = CreateStop.CreateStopHandler(repositoryMock)
        val command = CreateStop("stopIdentifier", "name", "code", LocationCoordinates(1.0, 1.0), LocationType.STATION, null)

        val result = handler.handle(command)

        verify(repositoryMock).save(argForWhich { stopIdentifier == command.stopIdentifier })
        assertThat(result.id).isEqualTo(generatedId)
        assertThat(result.stopIdentifier).isEqualTo(command.stopIdentifier)
        assertThat(result.name).isEqualTo(command.name)
        assertThat(result.code).isEqualTo(command.code)
        assertThat(result.locationCoordinates).isNotNull
        assertThat(result.locationCoordinates!!.latitude).isEqualTo(command.locationCoordinates!!.latitude)
        assertThat(result.locationCoordinates!!.longitude).isEqualTo(command.locationCoordinates!!.longitude)
        assertThat(result.locationType).isEqualTo(command.locationType)
        assertThat(result.parent).isNull()
    }
    @Test
    fun handle_whenCalledWithParentIdAndParentStopExists_createNewStop() {
        val generatedId = UUID.randomUUID()
        val parentId = UUID.randomUUID()
        val repositoryMock = mock<StopRepository> {
            onGeneric { save(any()) }.doAnswer { invocation: InvocationOnMock ->
                val stop = invocation.getArgument<Stop>(0)
                stop.id = generatedId
                stop
            }
            onGeneric { findById(any()) }.doReturn( Optional.of(parent!!))
        }
        val handler = CreateStop.CreateStopHandler(repositoryMock)
        val command = CreateStop("stopIdentifier", "name", "code", LocationCoordinates(1.0, 1.0), LocationType.STATION, parentId)

        val result = handler.handle(command)

        verify(repositoryMock).save(argForWhich { stopIdentifier == command.stopIdentifier })
        verify(repositoryMock).findById(parentId)
        assertThat(result.id).isEqualTo(generatedId)
        assertThat(result.stopIdentifier).isEqualTo(command.stopIdentifier)
        assertThat(result.name).isEqualTo(command.name)
        assertThat(result.code).isEqualTo(command.code)
        assertThat(result.locationCoordinates).isNotNull
        assertThat(result.locationCoordinates!!.latitude).isEqualTo(command.locationCoordinates!!.latitude)
        assertThat(result.locationCoordinates!!.longitude).isEqualTo(command.locationCoordinates!!.longitude)
        assertThat(result.locationType).isEqualTo(command.locationType)
        assertThat(result.parent).isNotNull
        assertThat(result.parent).isEqualTo(this.parent)
    }
    @Test
    fun handle_whenCalledWithParentIdAndParentStopDoesNotExists_createNewStop() {
        val generatedId = UUID.randomUUID()
        val parentId = UUID.randomUUID()
        val repositoryMock = mock<StopRepository> {
            onGeneric { save(any()) }.doAnswer { invocation: InvocationOnMock ->
                val stop = invocation.getArgument<Stop>(0)
                stop.id = generatedId
                stop
            }
            onGeneric { findById(any()) }.doReturn( Optional.empty())
        }
        val handler = CreateStop.CreateStopHandler(repositoryMock)
        val command = CreateStop("stopIdentifier", "name", "code", LocationCoordinates(1.0, 1.0), LocationType.STATION, parentId)

        val exception = assertThrows<EntityNotFoundException> { handler.handle(command) }

        assertThat(exception.message).contains("Stop")
        assertThat(exception.message).contains(parentId.toString())
    }
}
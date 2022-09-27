package de.floydkretschmar.opendatatransitvisualizer.application.stops.commands

import an.awesome.pipelinr.Command
import de.floydkretschmar.opendatatransitvisualizer.application.abstractions.repositories.StopRepository
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationCoordinates
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationType
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import org.springframework.stereotype.Component
import java.util.*

class CreateStop(
    val stopIdentifier: String,
    val name: String?,
    val code: String?,
    var locationCoordinates: LocationCoordinates? = null,
    val locationType: LocationType?,
    val parentId: UUID?
) : Command<Stop> {
    @Component
    class CreateStopHandler(val stopRepository: StopRepository) : Command.Handler<CreateStop, Stop> {
        override fun handle(request: CreateStop?): Stop {
            throw NotImplementedError()
        }
    }
}
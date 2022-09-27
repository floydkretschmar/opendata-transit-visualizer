package de.floydkretschmar.opendatatransitvisualizer.application.stops.commands

import an.awesome.pipelinr.Command
import de.floydkretschmar.opendatatransitvisualizer.application.abstractions.repositories.StopRepository
import de.floydkretschmar.opendatatransitvisualizer.application.exceptions.EntityNotFoundException
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
        override fun handle(request: CreateStop): Stop {
            var parent: Optional<Stop> = Optional.empty()
            if (request.parentId != null) {
                parent = stopRepository.findById(request.parentId)
                if (parent.isEmpty) throw EntityNotFoundException("Stop", request.parentId.toString())
            }

            val stopBuilder = Stop
                .Builder()
                .name(request.name)
                .code(request.code)
                .locationCoordinates(request.locationCoordinates)
                .locationType(request.locationType)

            if (parent.isPresent)
                stopBuilder.parent(parent.get())

            return stopRepository.save(stopBuilder.build(request.stopIdentifier))
        }
    }
}
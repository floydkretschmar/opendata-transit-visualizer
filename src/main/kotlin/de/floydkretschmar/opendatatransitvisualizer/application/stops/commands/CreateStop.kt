package de.floydkretschmar.opendatatransitvisualizer.application.stops.commands

import an.awesome.pipelinr.Command
import br.com.fluentvalidator.AbstractValidator
import de.floydkretschmar.opendatatransitvisualizer.application.abstractions.repositories.StopRepository
import de.floydkretschmar.opendatatransitvisualizer.application.exceptions.EntityNotFoundException
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.*
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
        override fun handle(command: CreateStop): Stop {
            var parent: Optional<Stop> = Optional.empty()
            if (command.parentId != null) {
                parent = stopRepository.findById(command.parentId)
                if (parent.isEmpty) throw EntityNotFoundException("Stop", command.parentId.toString())
            }

            val stopBuilder = Stop
                .Builder()
                .name(command.name)
                .code(command.code)
                .locationCoordinates(command.locationCoordinates)
                .locationType(command.locationType)

            if (parent.isPresent)
                stopBuilder.parent(parent.get())

            return stopRepository.save(stopBuilder.build(command.stopIdentifier))
        }
    }

    @Component
    class CreateStopValidator : AbstractValidator<CreateStop>() {
        override fun rules() {
            ruleFor { stop -> stop }
                .must { s: CreateStop -> !s.name.isNullOrEmpty() }
                .`when` { s -> isMainLocationType(s.locationType) }
                .withMessage { s -> "Name cannot be empty if locationType is set to '${s.locationType}'" }
            ruleFor { stop -> stop }
                .must { s: CreateStop -> s.locationCoordinates != null }
                .`when` { s -> isMainLocationType(s.locationType) }
                .withMessage { s -> "LocationCoordinates cannot be empty if locationType is set to '${s.locationType}'" }
            ruleFor { stop -> stop }
                .must { s: CreateStop -> s.parentId != null }
                .`when` { s -> isSubLocationType(s.locationType) }
                .withMessage { s -> "ParentId cannot be empty if locationType is set to '${s.locationType}'" }
        }
    }
}
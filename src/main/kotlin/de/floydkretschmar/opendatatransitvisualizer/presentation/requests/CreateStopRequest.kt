package de.floydkretschmar.opendatatransitvisualizer.presentation.requests

import de.floydkretschmar.opendatatransitvisualizer.application.stops.commands.CreateStop
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationCoordinates
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationType
import java.util.*

class CreateStopRequest(
    val stopIdentifier: String,
    val name: String?,
    val code: String?,
    var locationCoordinates: LocationCoordinates? = null,
    val locationType: LocationType?,
    val parentId: UUID?
) {
    fun toCommand() : CreateStop {
        return CreateStop(
            stopIdentifier,
            name,
            code,
            locationCoordinates,
            locationType,
            parentId
        )
    }
}
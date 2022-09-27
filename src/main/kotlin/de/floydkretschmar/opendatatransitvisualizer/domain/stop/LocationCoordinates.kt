package de.floydkretschmar.opendatatransitvisualizer.domain.stop

import de.floydkretschmar.opendatatransitvisualizer.domain.AutoIdEntity
import org.springframework.data.neo4j.core.schema.Node

@Node
class LocationCoordinates(
    val longitude: Double,
    val latitude: Double
) : AutoIdEntity() {
}
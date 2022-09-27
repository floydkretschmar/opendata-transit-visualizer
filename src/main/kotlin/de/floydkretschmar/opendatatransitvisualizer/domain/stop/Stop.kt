package de.floydkretschmar.opendatatransitvisualizer.domain.stop

import de.floydkretschmar.opendatatransitvisualizer.domain.AutoIdEntity
import de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation.Segment
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.util.*

@Node
class Stop private constructor(
    val stopIdentifier: String,
    val name: String?,
    val code: String?,
    @Relationship(type = "IS_LOCATED_AT", direction = Relationship.Direction.OUTGOING) var locationCoordinates: LocationCoordinates? = null,
    val locationType: LocationType?,
    @Relationship(type = "HAS_PARENT", direction = Relationship.Direction.OUTGOING) val parent: Stop?) : AutoIdEntity<UUID>() {

    data class Builder(
        var name: String? = null,
        var code: String? = null,
        var locationCoordinates: LocationCoordinates? = null,
        var locationType: LocationType? = null,
        var parent: Stop? = null,
        var connections: List<Segment> = listOf()) {

        fun name(name: String?) = apply { this.name = name }
        fun code(code: String?) = apply { this.code = code }
        fun locationCoordinates(locationCoordinates: LocationCoordinates?) = apply { this.locationCoordinates = locationCoordinates }
        fun locationType(locationType: LocationType?) = apply { this.locationType = locationType }
        fun parent(parent: Stop?) = apply { this.parent = parent }
        fun build(stopIdentifier: String): Stop {
            val stop = Stop(stopIdentifier, name, code, locationCoordinates, locationType, parent)
            validate(stop)
            return stop
        }
        private fun validate(stop: Stop) {
            if (stop.locationType == LocationType.STOP || stop.locationType == LocationType.STATION || stop.locationType == LocationType.ENTRANCE_EXIT || stop.locationType == null) {
                if (stop.name?.isEmpty() == true)
                    throw IllegalArgumentException("'name' has to be set for location type '$locationType'")
                if (stop.locationCoordinates == null)
                    throw IllegalArgumentException("'coordinate' has to be set for location type '$locationType'")
            }

            if (stop.locationType == LocationType.GENERIC_NODE || stop.locationType == LocationType.BOARDING_AREA || stop.locationType == LocationType.ENTRANCE_EXIT) {
                if (stop.parent == null)
                    throw IllegalArgumentException("'parent' has to be set for location type '$locationType'")
            }
        }
    }
}
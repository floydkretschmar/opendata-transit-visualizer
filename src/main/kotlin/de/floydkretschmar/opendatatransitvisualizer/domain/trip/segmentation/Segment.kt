package de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation

import de.floydkretschmar.opendatatransitvisualizer.domain.AutoIdEntity
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.util.*

@Node
class Segment(
    @Relationship(type = "DEPARTS_FROM", direction = Relationship.Direction.OUTGOING)
    val departure: ScheduledTravel,
    @Relationship(type = "ARRIVES_AT", direction = Relationship.Direction.OUTGOING)
    val arrival: ScheduledTravel,
    val pickupType: PickupType?,
    val dropOffType: DropOffType?,
    var headSign: String?
) : AutoIdEntity<UUID>()
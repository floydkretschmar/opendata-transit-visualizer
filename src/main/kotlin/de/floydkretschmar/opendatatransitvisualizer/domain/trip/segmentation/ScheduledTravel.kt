package de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation

import de.floydkretschmar.opendatatransitvisualizer.domain.AutoIdEntity
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode
import java.time.LocalTime

@RelationshipProperties
class ScheduledTravel(
    @TargetNode
    val stop: Stop,
    val time: LocalTime
) : AutoIdEntity<Long>() {
}
package de.floydkretschmar.opendatatransitvisualizer.domain.trip

import de.floydkretschmar.opendatatransitvisualizer.domain.AutoIdEntity
import de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation.Segment
import org.springframework.data.neo4j.core.schema.RelationshipProperties
import org.springframework.data.neo4j.core.schema.TargetNode

@RelationshipProperties
class Leg(@TargetNode val segment: Segment, val segmentIndex: Int) : AutoIdEntity<Long>()
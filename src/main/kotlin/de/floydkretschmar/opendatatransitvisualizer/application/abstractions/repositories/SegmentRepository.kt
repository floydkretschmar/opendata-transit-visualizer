package de.floydkretschmar.opendatatransitvisualizer.application.abstractions.repositories

import de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation.Segment
import org.springframework.data.neo4j.repository.Neo4jRepository
import java.util.*

interface SegmentRepository : Neo4jRepository<Segment, UUID> {
}
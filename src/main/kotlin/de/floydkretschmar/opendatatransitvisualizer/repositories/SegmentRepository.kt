package de.floydkretschmar.opendatatransitvisualizer.repositories

import de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation.Segment
import org.springframework.data.neo4j.repository.Neo4jRepository

interface SegmentRepository : Neo4jRepository<Segment, Long> {
}
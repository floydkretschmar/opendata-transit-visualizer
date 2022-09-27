package de.floydkretschmar.opendatatransitvisualizer.repositories

import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import org.springframework.data.neo4j.repository.Neo4jRepository

interface StopRepository : Neo4jRepository<Stop, String> {
}
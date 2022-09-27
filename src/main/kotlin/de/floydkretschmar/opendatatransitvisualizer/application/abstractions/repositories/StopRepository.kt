package de.floydkretschmar.opendatatransitvisualizer.application.abstractions.repositories

import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import org.springframework.data.neo4j.repository.Neo4jRepository
import java.util.*

interface StopRepository : Neo4jRepository<Stop, UUID>
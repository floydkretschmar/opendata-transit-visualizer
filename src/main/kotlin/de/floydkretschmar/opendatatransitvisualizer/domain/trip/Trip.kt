package de.floydkretschmar.opendatatransitvisualizer.domain.trip

import de.floydkretschmar.opendatatransitvisualizer.domain.AutoIdEntity
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship
import java.util.*

@Node
class Trip(val tripIdentifier: String) : AutoIdEntity<UUID>() {
    @Relationship(type = "HAS_LEGS", direction = Relationship.Direction.OUTGOING)
    val legs: List<Leg> = listOf()
}
package de.floydkretschmar.opendatatransitvisualizer.domain.trip

import de.floydkretschmar.opendatatransitvisualizer.domain.VersionableEntity
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
class Trip(@Id val id: String) : VersionableEntity() {
    @Relationship(type = "HAS_LEGS", direction = Relationship.Direction.OUTGOING)
    val legs: List<Leg> = listOf()
}
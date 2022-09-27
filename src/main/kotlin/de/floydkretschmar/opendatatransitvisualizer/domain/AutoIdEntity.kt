package de.floydkretschmar.opendatatransitvisualizer.domain

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id

open class AutoIdEntity() : VersionableEntity() {
    @Id
    @GeneratedValue
    var id: Long? = null
}
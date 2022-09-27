package de.floydkretschmar.opendatatransitvisualizer.domain

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id

open class AutoIdEntity<T>() : VersionableEntity() {
    @Id
    @GeneratedValue
    var id: T? = null
}
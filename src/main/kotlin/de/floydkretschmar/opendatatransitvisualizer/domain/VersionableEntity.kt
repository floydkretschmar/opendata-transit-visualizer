package de.floydkretschmar.opendatatransitvisualizer.domain

import org.springframework.data.annotation.Version

open class VersionableEntity() {
    @Version var version: Long = 0
}
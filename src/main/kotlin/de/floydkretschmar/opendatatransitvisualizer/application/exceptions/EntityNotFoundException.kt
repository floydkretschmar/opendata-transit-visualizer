package de.floydkretschmar.opendatatransitvisualizer.application.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class EntityNotFoundException(entityType: String, entityId: String) :
    Exception("Entity of type '${entityType}' with id '${entityId}' does not exist.") {
}
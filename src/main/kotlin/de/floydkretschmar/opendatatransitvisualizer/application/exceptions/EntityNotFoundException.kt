package de.floydkretschmar.opendatatransitvisualizer.application.exceptions

class EntityNotFoundException(entityType: String, entityId: String) :
    Exception("Entity of type '${entityType}' with id '${entityId}' does not exist.") {
}
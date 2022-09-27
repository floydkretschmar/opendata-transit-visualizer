package de.floydkretschmar.opendatatransitvisualizer.domain.trip.segmentation

enum class DropOffType(val value: Int) {
    REGULAR(0),
    NOT_AVAILABLE(1),
    MUST_COORDINATE_WITH_AGENCY(2),
    MUST_COORDINATE_WITH_DRIVER(3)
}

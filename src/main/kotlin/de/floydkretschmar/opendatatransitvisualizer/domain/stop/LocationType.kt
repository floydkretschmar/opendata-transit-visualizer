package de.floydkretschmar.opendatatransitvisualizer.domain.stop

enum class LocationType(val value: Int) {
    STOP(0),
    STATION(1),
    ENTRANCE_EXIT(2),
    GENERIC_NODE(3),
    BOARDING_AREA(4)
}

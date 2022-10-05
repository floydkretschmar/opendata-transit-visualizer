package de.floydkretschmar.opendatatransitvisualizer.domain.stop

enum class LocationType(val value: Int) {
    STOP(0),
    STATION(1),
    ENTRANCE_EXIT(2),
    GENERIC_NODE(3),
    BOARDING_AREA(4);
}

fun isMainLocationType(locationType: LocationType?): Boolean {
    return locationType == LocationType.STOP || locationType == LocationType.STATION || locationType == LocationType.ENTRANCE_EXIT || locationType == null
}

fun isSubLocationType(locationType: LocationType?): Boolean {
    return locationType == LocationType.GENERIC_NODE || locationType == LocationType.BOARDING_AREA || locationType == LocationType.ENTRANCE_EXIT
}

package de.floydkretschmar.opendatatransitvisualizer.application.stops.commands

import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationCoordinates
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.LocationType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.util.*

class CreateStopValidatorTests {

    @ParameterizedTest
    @EnumSource(value = LocationType::class, names = arrayOf("STOP", "STATION", "ENTRANCE_EXIT"))
    fun name_whenNotSetForMainLocationType_returnError(locationType: LocationType) {
        val validator = CreateStop.CreateStopValidator()
        val command = CreateStop("stopIdentifier", null, "code", LocationCoordinates(1.0, 1.0), locationType, UUID.randomUUID())

        val result = validator.validate(command)

        assertThat(result.isValid).isFalse
    }

    @Test
    fun name_whenNotSetEmptyLocationType_returnError() {
        val validator = CreateStop.CreateStopValidator()
        val command = CreateStop("stopIdentifier", null, "code", LocationCoordinates(1.0, 1.0), null, UUID.randomUUID())

        val result = validator.validate(command)

        assertThat(result.isValid).isFalse
    }

    @ParameterizedTest
    @EnumSource(value = LocationType::class, names = arrayOf("STOP", "STATION", "ENTRANCE_EXIT"))
    fun locationCoordinates_whenNotSetForMainLocationType_returnError(locationType: LocationType) {
        val validator = CreateStop.CreateStopValidator()
        val command = CreateStop("stopIdentifier", "name", "code", null, locationType, UUID.randomUUID())

        val result = validator.validate(command)

        assertThat(result.isValid).isFalse
    }

    @Test
    fun locationCoordinates_whenNotSetEmptyLocationType_returnError() {
        val validator = CreateStop.CreateStopValidator()
        val command = CreateStop("stopIdentifier", "name", "code", null, null, UUID.randomUUID())

        val result = validator.validate(command)

        assertThat(result.isValid).isFalse
    }

    @ParameterizedTest
    @EnumSource(value = LocationType::class, names = arrayOf("GENERIC_NODE", "BOARDING_AREA", "ENTRANCE_EXIT"))
    fun parentId_whenNotSetForSubLocationType_returnError(locationType: LocationType) {
        val validator = CreateStop.CreateStopValidator()
        val command = CreateStop("stopIdentifier", "name", "code", LocationCoordinates(1.0, 1.0), locationType, null)

        val result = validator.validate(command)

        assertThat(result.isValid).isFalse
    }
}
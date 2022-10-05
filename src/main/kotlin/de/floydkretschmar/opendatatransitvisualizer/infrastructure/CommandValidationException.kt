package de.floydkretschmar.opendatatransitvisualizer.infrastructure

import br.com.fluentvalidator.context.ValidationResult
import br.com.fluentvalidator.exception.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class CommandValidationException(validationResult: ValidationResult?) : ValidationException(validationResult) {
}
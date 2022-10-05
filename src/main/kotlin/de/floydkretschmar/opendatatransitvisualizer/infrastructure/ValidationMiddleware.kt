package de.floydkretschmar.opendatatransitvisualizer.infrastructure

import an.awesome.pipelinr.Command
import br.com.fluentvalidator.AbstractValidator
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component
import java.lang.reflect.ParameterizedType

@Component
class ValidationMiddleware(private val validators: ObjectProvider<AbstractValidator<*>>) : Command.Middleware {

    override fun <R, C : Command<R>?> invoke(
        command: C,
        next: Command.Middleware.Next<R>
    ): R {
        // validate command here
        val validator = validators.stream().filter { v: AbstractValidator<*> ->
            val superClassType = v::class.java.genericSuperclass
            superClassType is ParameterizedType &&
                    superClassType.actualTypeArguments.size > 0 &&
                    superClassType.actualTypeArguments[0] == command!!.javaClass
        }.findFirst()

        if (validator.isPresent) {
            val result = (validator.get() as AbstractValidator<C>).validate(command)
            result.isInvalidThrow(CommandValidationException::class.java)
        }

        return next.invoke()
    }
}
package de.floydkretschmar.opendatatransitvisualizer.presentation.controllers

import an.awesome.pipelinr.Pipeline
import de.floydkretschmar.opendatatransitvisualizer.domain.stop.Stop
import de.floydkretschmar.opendatatransitvisualizer.presentation.requests.CreateStopRequest
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/stops")
class StopController(val mediator: Pipeline) {
    @PutMapping
    fun createStop(@RequestBody createStopRequest: CreateStopRequest): Stop? {
        var command = createStopRequest.toCommand()
        return command.execute(mediator)
    }
}
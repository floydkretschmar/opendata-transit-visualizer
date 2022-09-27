package de.floydkretschmar.opendatatransitvisualizer

import an.awesome.pipelinr.*
import org.springframework.beans.factory.ObjectProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenDataTransitVisualizerConfiguration {
    @Bean
    fun pipeline(
        commandHandlers: ObjectProvider<Command.Handler<*, *>?>,
        notificationHandlers: ObjectProvider<Notification.Handler<*>>,
        middlewares: ObjectProvider<Command.Middleware?>
    ): Pipeline? {
        return Pipelinr()
            .with(CommandHandlers { commandHandlers.stream() })
            .with(NotificationHandlers { notificationHandlers.stream() })
            .with(Command.Middlewares { middlewares.orderedStream() })
    }
}
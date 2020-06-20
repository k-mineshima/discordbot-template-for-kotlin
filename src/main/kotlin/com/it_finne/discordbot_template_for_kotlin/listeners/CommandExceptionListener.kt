package com.it_finne.discordbot_template_for_kotlin.listeners

import com.it_finne.discordbot_template_for_kotlin.errors.CommandErrorException
import com.it_finne.discordbot_template_for_kotlin.errors.CommandWarningException
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.jagrosh.jdautilities.command.CommandListener
import mu.KLogger
import mu.KotlinLogging

private val logger: KLogger = KotlinLogging.logger {}

class CommandExceptionListener: CommandListener {
    override fun onCommandException(event: CommandEvent, command: Command, throwable: Throwable) {
        when (throwable) {
            is CommandErrorException -> {
                event.replyError(throwable.message)
            }
            is CommandWarningException -> {
                event.replyWarning(throwable.message)
            }
            else -> {
                logger.error(throwable) { "An exception occurred in a command. command=${command.name}, guild=${event.guild.id}" }
                event.replyError("An unknown error has occurred. Please try again later.")
            }
        }
    }
}

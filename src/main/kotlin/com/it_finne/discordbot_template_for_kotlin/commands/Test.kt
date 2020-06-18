package com.it_finne.discordbot_template_for_kotlin.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

class Test: Command() {
    init {
        this.name = "test"
        this.help = "test command."
    }

    override fun execute(event: CommandEvent) {
        event.reply("hello! discordbot with kotlin.")
    }
}

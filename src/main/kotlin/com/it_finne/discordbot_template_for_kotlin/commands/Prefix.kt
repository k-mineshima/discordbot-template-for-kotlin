package com.it_finne.discordbot_template_for_kotlin.commands

import com.it_finne.discordbot_template_for_kotlin.entity.GuildRecord
import com.it_finne.discordbot_template_for_kotlin.entity.GuildRecords
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.entities.Guild
import org.jetbrains.exposed.sql.transactions.transaction

class Prefix: Command() {
    init {
        this.name = "prefix"
    }

    override fun execute(event: CommandEvent) {
        val guild: Guild = event.guild

        transaction {
            GuildRecord.find { GuildRecords.guildId eq guild.idLong }.first().apply {
                prefix = event.args
            }
        }
        event.reply("the prefix has been updated successfully.")
    }
}

package com.it_finne.discordbot_template_for_kotlin.application.repository

import arrow.core.Either
import arrow.core.Option
import arrow.core.firstOrNone
import com.it_finne.discordbot_template_for_kotlin.application.entity.GuildEntity
import com.it_finne.discordbot_template_for_kotlin.application.table.GuildTable
import com.it_finne.discordbot_template_for_kotlin.config.Application
import com.it_finne.discordbot_template_for_kotlin.error.InsertFailedException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class GuildRepository {
    fun insert(
        guildId: Long,
        name: String,
        prefix: String = Application.configuration.discordbot.defaultPrefix
    ): Either<InsertFailedException, GuildEntity> = transaction {
        GuildTable.insert {
            it[GuildTable.guildId] = guildId
            it[GuildTable.name] = name
            it[GuildTable.prefix] = prefix
        }
        .resultedValues
        .orEmpty()
        .firstOrNone()
        .map(GuildTable::toEntity)
        .toEither { InsertFailedException("failed to insert record. guild_id=$guildId, guild_name=$name") }
    }

    fun update(guildId: Long, name: String? = null, prefix: String? = null): Int = transaction {
        GuildTable.update({ GuildTable.guildId eq guildId}) {
            name?.also {
                name -> it[GuildTable.name] = name
            }
            prefix?.also {
                prefix -> it[GuildTable.prefix] = prefix
            }
        }
    }

    fun insertOrUpdate(
        guildId: Long,
        name: String
    ) {
        if (this.getByGuildId(guildId).isEmpty()) {
            this.insert(guildId, name)
        }
        else {
            this.update(guildId, name=name)
        }
    }

    fun getByGuildId(guildId: Long): Option<GuildEntity> = transaction {
        GuildTable.select { GuildTable.guildId eq guildId }.map(GuildTable::toEntity).firstOrNone()
    }

    fun delete(vararg guildIds: Long) = transaction {
        guildIds.forEach {
            GuildTable.deleteWhere { GuildTable.guildId eq it }
        }
    }

    fun getAll(): List<GuildEntity> = transaction {
        GuildTable.selectAll().map(GuildTable::toEntity)
    }
}
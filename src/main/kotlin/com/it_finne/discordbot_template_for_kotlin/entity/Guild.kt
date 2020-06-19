package com.it_finne.discordbot_template_for_kotlin.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object GuildRecords: LongIdTable(name="guilds") {
    val guildId: Column<Long> = long("guild_id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val prefix: Column<String> = varchar("prefix", 255)

    override val primaryKey = PrimaryKey(id)
}

class GuildRecord(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<GuildRecord>(GuildRecords)

    var guildId: Long by GuildRecords.guildId
    var name: String by GuildRecords.name
    var prefix: String by GuildRecords.prefix
}

package com.it_finne.discordbot_template_for_kotlin.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object GuildRecords: LongIdTable(name="guilds") {
    val guildId: Column<Long> = long("guild_id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val prefix: Column<String> = varchar("prefix", 255)
    val createdAt: Column<LocalDateTime> = datetime("created_at").default(LocalDateTime.now())
    val updatedAt: Column<LocalDateTime> = datetime("updated_at").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)
}

class GuildRecord(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<GuildRecord>(GuildRecords)

    var guildId: Long by GuildRecords.guildId
    var name: String by GuildRecords.name
    var prefix: String by GuildRecords.prefix
    var createdAt: LocalDateTime by GuildRecords.createdAt
    var updatedAt: LocalDateTime by GuildRecords.updatedAt
}

package com.it_finne.discordbot_template_for_kotlin.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object Guilds: LongIdTable(name="guilds") {
    val guildId: Column<Long> = long("guild_id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val prefix: Column<String> = varchar("prefix", 255)

    override val primaryKey = PrimaryKey(id)
}

class Guild(id: EntityID<Long>): LongEntity(id) {
    companion object: LongEntityClass<Guild>(Guilds)

    var guildId: Long by Guilds.guildId
    var name: String by Guilds.name
    var prefix: String by Guilds.prefix
}

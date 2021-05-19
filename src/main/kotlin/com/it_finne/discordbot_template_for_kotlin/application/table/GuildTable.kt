package com.it_finne.discordbot_template_for_kotlin.application.table

import com.it_finne.discordbot_template_for_kotlin.application.entity.GuildEntity
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object GuildTable: LongIdTable(name="guilds") {
    val guildId: Column<Long> = long("guild_id").uniqueIndex()
    val name: Column<String> = varchar("name", 255)
    val prefix: Column<String> = varchar("prefix", 255)
    val createdAt: Column<LocalDateTime> = datetime("created_at").default(LocalDateTime.now())
    val updatedAt: Column<LocalDateTime> = datetime("updated_at").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id)

    fun toEntity(resultRow: ResultRow): GuildEntity {
        return GuildEntity(
            resultRow[id].value,
            resultRow[guildId],
            resultRow[name],
            resultRow[prefix],
            resultRow[createdAt],
            resultRow[updatedAt]
        )
    }
}

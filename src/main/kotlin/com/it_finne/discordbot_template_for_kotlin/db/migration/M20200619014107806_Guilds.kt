package com.it_finne.discordbot_template_for_kotlin.db.migration

import com.improve_future.harmonica.core.AbstractMigration
import com.it_finne.discordbot_template_for_kotlin.entity.Guilds
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop

class M20200619014107806_Guilds : AbstractMigration() {
    override fun up() {
        create(Guilds)
    }

    override fun down() {
        drop(Guilds)
    }
}

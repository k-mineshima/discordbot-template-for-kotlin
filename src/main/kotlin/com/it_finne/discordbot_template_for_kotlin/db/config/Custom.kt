package com.it_finne.discordbot_template_for_kotlin.db.config

import com.improve_future.harmonica.core.DbConfig
import com.it_finne.discordbot_template_for_kotlin.conf.Application

class Custom: DbConfig({
    dbms = getUsedDbms()
    dbName = Application.configuration.database.database
    host = Application.configuration.database.host
    user = Application.configuration.database.user
    password = Application.configuration.database.password
})

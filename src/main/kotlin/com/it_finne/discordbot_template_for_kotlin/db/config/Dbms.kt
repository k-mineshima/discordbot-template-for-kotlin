package com.it_finne.discordbot_template_for_kotlin.db.config

import com.improve_future.harmonica.core.Dbms
import com.it_finne.discordbot_template_for_kotlin.conf.Application

fun getUsedDbms(): Dbms {
    return when (Application.database.vendor) {
        "h2" -> Dbms.H2
        "postgresql" -> Dbms.PostgreSQL
        "mysql" -> Dbms.MySQL
        "oracle" -> Dbms.Oracle
        "sqlite" -> Dbms.SQLite
        "sqlserver" -> Dbms.SQLServer
        else -> error("unsupported vendor: ${Application.database.vendor}")
    }
}

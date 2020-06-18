package com.it_finne.discordbot_template_for_kotlin.conf

import com.it_finne.discordbot_template_for_kotlin.ApplicationMode
import com.it_finne.discordbot_template_for_kotlin.errors.IllegalEnvironmentException
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object Application {
    val applicationMode: ApplicationMode
    val configuration: Configuration
    val database: Database

    init {
        val environment: String = System.getenv("DQX_DRAKEE_ENVIRONMENT") ?: "development"
        applicationMode = ApplicationMode.get(environment)
            ?: throw IllegalEnvironmentException("invalid environment: ${environment}. please choice production, staging or development.")
        configuration = Configuration.load(applicationMode)

        val hikariConfig = HikariConfig().apply {
            jdbcUrl = "jdbc:${configuration.database.vendor}://${configuration.database.host}/${configuration.database.database}"
            driverClassName = configuration.database.driver
            username = configuration.database.user
            password = configuration.database.password
            maximumPoolSize = configuration.database.poolSize
        }

        this.database = Database.connect(HikariDataSource(hikariConfig))
    }
}

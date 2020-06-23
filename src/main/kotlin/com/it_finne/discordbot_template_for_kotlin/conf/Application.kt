package com.it_finne.discordbot_template_for_kotlin.conf

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import com.it_finne.discordbot_template_for_kotlin.ApplicationMode
import com.it_finne.discordbot_template_for_kotlin.errors.IllegalEnvironmentException
import com.jagrosh.jdautilities.command.Command
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.reflections.Reflections
import org.slf4j.LoggerFactory

private const val ENVIRONMENT_NAME = "ENVIRONMENT"
private const val ENVIRONMENT_DEFAULT_VALUE = "development"

object Application {
    val applicationMode: ApplicationMode
    val configuration: Configuration
    val database: Database
    val commands: Array<Command>

    init {
        this.applicationMode = this.getApplicationModeFromEnvironment().apply {
            configureLogging(this)
        }

        this.configuration = Configuration.load(applicationMode)
        this.database = this.getDatabaseFromConfiguration()
        this.commands = this.getCommandsFromConfiguration()
    }

    private fun configureLogging(applicationMode: ApplicationMode) {
        val loggerContext: LoggerContext = (LoggerFactory.getILoggerFactory() as LoggerContext).apply {
            reset()
        }

        val filename: String = "/logback-${applicationMode.value}.xml"
        JoranConfigurator().apply {
            context = loggerContext
            doConfigure(Application::class.java.getResource(filename))
        }
    }

    private fun getApplicationModeFromEnvironment(): ApplicationMode {
        val environmentValue: String = System.getenv(ENVIRONMENT_NAME) ?: ENVIRONMENT_DEFAULT_VALUE

        return ApplicationMode.get(environmentValue) ?: throw IllegalEnvironmentException(environmentValue)
    }

    private fun getDatabaseFromConfiguration(): Database {
        val hikariConfig: HikariConfig = HikariConfig().apply {
            jdbcUrl = "jdbc:${configuration.database.vendor}://${configuration.database.host}/${configuration.database.database}?serverTimezone=${configuration.database.timezone}"
            driverClassName = configuration.database.driver
            username = configuration.database.user
            password = configuration.database.password
            maximumPoolSize = configuration.database.poolSize
        }

        return Database.connect(HikariDataSource(hikariConfig))
    }

    private fun getCommandsFromConfiguration(): Array<Command> {
        val reflections: Reflections = Reflections(this.configuration.discordbot.commandsPackage)
        val commandClasses: Set<Class<*>> = reflections.getSubTypesOf(Command::class.java)
        return commandClasses.map {
            it.getConstructor().newInstance() as Command
        }.toTypedArray()
    }

}

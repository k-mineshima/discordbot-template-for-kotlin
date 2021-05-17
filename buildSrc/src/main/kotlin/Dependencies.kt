import org.gradle.api.artifacts.dsl.DependencyHandler

// Align versions of all Kotlin components
val DependencyHandler.kotlinBom get() = platform("org.jetbrains.kotlin:kotlin-bom")
// Use the Kotlin JDK 8 standard library.
val DependencyHandler.kotlinStdlibJdk8 get() = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
// Use the Kotlin test library.
val DependencyHandler.kotlinTest get() = "org.jetbrains.kotlin:kotlin-test"
// Use the Kotlin JUnit integration.
val DependencyHandler.kotlinTestJUnit get() = "org.jetbrains.kotlin:kotlin-test-junit"
val DependencyHandler.jda get() = "net.dv8tion:JDA:${Versions.Dependency.JDA}"
val DependencyHandler.jdaUtilities get() = "com.jagrosh:jda-utilities:${Versions.Dependency.JDA_UTILITIES}"
val DependencyHandler.kaml get() = "com.charleskorn.kaml:kaml:${Versions.Dependency.KAML}"
val DependencyHandler.kotlinxSerializationJson get() = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Dependency.KOTLINX_SERIALIZATION_JSON}"
val DependencyHandler.exposedCore get() = "org.jetbrains.exposed:exposed-core:${Versions.Dependency.EXPOSED}"
val DependencyHandler.exposedDao get() = "org.jetbrains.exposed:exposed-dao:${Versions.Dependency.EXPOSED}"
val DependencyHandler.exposedJdbc get() = "org.jetbrains.exposed:exposed-jdbc:${Versions.Dependency.EXPOSED}"
val DependencyHandler.exposedJavaTime get() = "org.jetbrains.exposed:exposed-java-time:${Versions.Dependency.EXPOSED_JAVA_TIME}"
val DependencyHandler.mysqlConnectorJava get() = "mysql:mysql-connector-java:${Versions.Dependency.MYSQL_CONNECTOR_JAVA}"
val DependencyHandler.hikariCP get() = "com.zaxxer:HikariCP:${Versions.Dependency.HIKARI_CP}"
val DependencyHandler.kotlinLogging get() = "io.github.microutils:kotlin-logging:${Versions.Dependency.KOTLIN_LOGGING}"
val DependencyHandler.logbackClassic get() = "ch.qos.logback:logback-classic:${Versions.Dependency.LOGBACK_CLASSIC}"

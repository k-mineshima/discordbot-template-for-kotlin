/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/* =============== PROJECT SETTINGS =============== */
val projectPackage: String = "com.it_finne.{PROJECT_NAME_LOWER_CASE}"
val projectMainClass: String = "AppKt"
/* ================================================ */

plugins {
    jvm
    kotlinSerialization
    flyway
    application
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlinBom)
    implementation(kotlinStdlibJdk8)
    testImplementation(kotlinTest)
    testImplementation(kotlinTestJUnit)
    implementation(jda)
    implementation(jdaUtilities)
    implementation(kaml)
    implementation(kotlinxSerializationJson)
    implementation(exposedCore)
    implementation(exposedDao)
    implementation(exposedJdbc)
    implementation(exposedJavaTime)
    implementation(mysqlConnectorJava)
    implementation(hikariCP)
    implementation(kotlinLogging)
    implementation(logbackClassic)
    implementation(arrowCore)
}

application {
    // Define the main class for the application.
    mainClass.set("${projectPackage}.${projectMainClass}")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "${projectPackage}.${projectMainClass}"
    }

    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.wrapper {
    gradleVersion = GRADLE_VERSION
}

flyway {
    url = System.getenv("{PROJECT_NAME_UPPER_CASE}_FLYWAY_URL")
    user = System.getenv("{PROJECT_NAME_UPPER_CASE}_FLYWAY_USER")
    password = System.getenv("{PROJECT_NAME_UPPER_CASE}_FLYWAY_PASSWORD")
}

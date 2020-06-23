package com.it_finne.discordbot_template_for_kotlin.conf

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Database(
    val vendor: String,
    val driver: String,
    val host: String,
    val database: String,
    val user: String,
    val password: String,
    val timezone: String,
    @SerialName("pool-size")
    val poolSize: Int
)

package com.it_finne.discordbot_template_for_kotlin

enum class ApplicationMode(val value: String) {
    PRODUCTION("production"),
    STAGING("staging"),
    DEVELOPMENT("development");

    companion object {
        fun get(modeValue: String): ApplicationMode? {
            for (mode in values()) {
                if (mode.value != modeValue) {
                    continue
                }
                return mode
            }
            return null
        }
    }
}

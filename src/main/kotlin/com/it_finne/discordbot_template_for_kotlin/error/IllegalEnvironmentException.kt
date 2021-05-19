package com.it_finne.discordbot_template_for_kotlin.error

import java.lang.RuntimeException

class IllegalEnvironmentException(environmentValue: String) : RuntimeException(
    "invalid environment: ${environmentValue}. please choice production, staging or development."
)

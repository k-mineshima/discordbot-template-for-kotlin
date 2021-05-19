package com.it_finne.discordbot_template_for_kotlin.lib.extension

import arrow.core.Either
import arrow.core.getOrHandle

fun <A: Throwable, B> Either<A, B>.getOrThrow(): B = getOrHandle { throw it }

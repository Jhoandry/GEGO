package com.lugloc.utils.config

import org.koin.core.KoinApplication
import org.koin.core.error.KoinAppAlreadyStartedException

object KoinApplicationGlobalContext {

    enum class KoinMessage {
        KOIN_NOT_STARTED,
        KOIN_ALREADY_STARTED;
    }

    var app: KoinApplication? = null

    /**
     * StandAlone Koin App instance
     */
    @JvmStatic
    fun get(): KoinApplication = app ?: error(KoinMessage.KOIN_NOT_STARTED.name)

    /**
     * StandAlone Koin App instance
     */
    @JvmStatic
    fun getOrNull(): KoinApplication? = app

    /**
     * Start a Koin Application as StandAlone
     */
    @JvmStatic
    fun start(koinApplication: KoinApplication) {
        if (app != null) {
            throw KoinAppAlreadyStartedException(KoinMessage.KOIN_ALREADY_STARTED.name)
        }
        app = koinApplication
    }

    /**
     * Stop current StandAlone Koin application
     */
    @JvmStatic
    fun stop() = synchronized(this) {
        app?.close()
        app = null
    }
}
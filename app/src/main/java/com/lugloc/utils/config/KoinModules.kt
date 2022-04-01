package com.lugloc.utils.config

import com.lugloc.services.UserOperationService
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCasesModule = module {
    single { UserOperationService() }
}

val networkingModule = module {
    single { NetworkConnectionManager.createRetrofitInstance() }
    single(named("ack")) { NetworkConnectionManager.createRetrofitInstanceForAck() }
}

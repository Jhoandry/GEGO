package com.lugloc.services.model.request

import com.lugloc.BuildConfig
import com.lugloc.utils.data.StringUtils.EMPTY_STRING

class AccountRequest internal constructor(
        internal var email: String? = EMPTY_STRING,
        internal var password: String? = EMPTY_STRING,
        internal var name: String? = EMPTY_STRING,
        internal val clientId: String = BuildConfig.CLIENT_ID,
        internal val platform: String? = BuildConfig.PLATFORM
)
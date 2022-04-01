package com.lugloc.services.model.response

import com.google.gson.annotations.SerializedName
import com.lugloc.utils.data.StringUtils.EMPTY_STRING

data class SimpleResponse(
    @SerializedName("message") val message: String = EMPTY_STRING,
    @SerializedName("status") val status: String = EMPTY_STRING,
    @SerializedName("code") val code: String = EMPTY_STRING
)
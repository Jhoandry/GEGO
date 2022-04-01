package com.lugloc.services.api

import com.lugloc.services.model.request.AccountRequest
import com.lugloc.services.model.response.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface AccountApi {

    @POST("/api/account/register")
    fun createAccount(@Body request: AccountRequest): Call<SimpleResponse>

}

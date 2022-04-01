package com.lugloc.services

import com.lugloc.services.model.response.baseMessage.BackendErrorResponse
import com.lugloc.services.api.AccountApi
import com.lugloc.services.model.request.AccountRequest
import com.lugloc.services.model.response.SimpleResponse
import com.lugloc.services.model.response.UseCaseResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import java.io.IOException

class UserOperationService : KoinComponent {

    private val retrofit: Retrofit by inject()

    fun sendUserAccount(accountRequest: AccountRequest): UseCaseResponse<SimpleResponse> {
        val call = retrofit.create(AccountApi::class.java).createAccount(accountRequest)
        return try {
            val serviceResponse = call.execute()
            if (serviceResponse.isSuccessful) {
                val createDriverAccountResponse = serviceResponse.body()
                if (createDriverAccountResponse != null) {
                    UseCaseResponse.Success(serviceResponse.body()!!)
                } else {
                    UseCaseResponse.Failure(Exception(BackendErrorResponse.NULL_BODY_ERROR.textValue))
                }
            } else {
                UseCaseResponse.Failure(Exception(BackendErrorResponse.RESPONSE_NOT_SUCCESSFUL.textValue))
            }
        } catch (e: IOException) {
            UseCaseResponse.Failure(e)
        } catch (e: RuntimeException) {
            UseCaseResponse.Failure(e)
        }
    }
}

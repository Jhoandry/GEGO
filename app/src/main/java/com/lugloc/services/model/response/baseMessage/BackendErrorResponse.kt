package com.lugloc.services.model.response.baseMessage

enum class BackendErrorResponse(val textValue: String) {
    RESPONSE_NOT_SUCCESSFUL("Response was not successful"),
    INVALID_RESPONSE_SERVER("Invalid response from the server"),
    NULL_BODY_ERROR("Null body error"),
    NETWORK_ERROR("Error de conexión");
}
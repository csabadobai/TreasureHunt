package com.example.csabadobai.treasurehunt.data.mappers

import com.example.csabadobai.treasurehunt.data.model.ServerResponse
import com.example.csabadobai.treasurehunt.net.response.UserLoginResponse

/**
 * Created by csaba.dobai on 03-11-2017.
 */
class UserLoginMapper {
    fun convertFromDataModel(response: UserLoginResponse): ServerResponse {
        return ServerResponse(checkResponse(response), buildMessage(response))
    }

    private fun buildMessage(response: UserLoginResponse): String {
        return if (checkResponse(response)) {
            "success"
        } else {
            "Please check username or password"
        }
    }

    private fun checkResponse(response: UserLoginResponse): Boolean {
        var isSuccess = true
        if (response.success.isNullOrBlank()) {
            isSuccess = false
        }
        return isSuccess
    }
}
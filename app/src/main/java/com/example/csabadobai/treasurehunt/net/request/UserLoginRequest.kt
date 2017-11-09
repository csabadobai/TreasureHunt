package com.example.csabadobai.treasurehunt.net.request

import com.example.csabadobai.treasurehunt.net.response.UserLoginResponse
import com.google.gson.Gson

/**
 * Created by csaba.dobai on 03-11-2017.
 */
class UserLoginRequest(private val username: String, private val password: String) {
    companion object {
        private val urlCommand = "user/login"
        private val methodType = "POST"
        private val params = HashMap<String, String>()
    }

    fun execute(): UserLoginResponse {
        params.put("username", username)
        params.put("password", password)
        val userLoginJsonStr = HttpRequestHandler(urlCommand, methodType, params).execute()
        return Gson().fromJson(userLoginJsonStr, UserLoginResponse::class.java)
    }
}
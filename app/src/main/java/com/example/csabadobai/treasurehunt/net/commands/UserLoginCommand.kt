package com.example.csabadobai.treasurehunt.net.commands

import com.example.csabadobai.treasurehunt.data.mappers.UserLoginMapper
import com.example.csabadobai.treasurehunt.data.model.ServerResponse
import com.example.csabadobai.treasurehunt.net.request.UserLoginRequest

/**
 * Created by csaba.dobai on 03-11-2017.
 */
class UserLoginCommand(private val username: String, private val password: String): Command<ServerResponse> {
    override fun execute(): ServerResponse {
        val userLoginRequest = UserLoginRequest(username, password)
        return UserLoginMapper().convertFromDataModel(userLoginRequest.execute())
    }
}
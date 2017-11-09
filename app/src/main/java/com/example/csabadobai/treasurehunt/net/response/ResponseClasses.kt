package com.example.csabadobai.treasurehunt.net.response

/**
 * Created by csaba.dobai on 03-11-2017.
 */

data class UserLoginResponse(val success: String, val errorCode: Int, val errorMessage: String, val data: Success)

data class Success(val userId: String, val authorizationToken: String)
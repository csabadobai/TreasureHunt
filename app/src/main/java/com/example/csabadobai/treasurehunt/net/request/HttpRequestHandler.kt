package com.example.csabadobai.treasurehunt.net.request

import org.json.JSONObject
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by csaba.dobai on 03-11-2017.
 */
class HttpRequestHandler(urlCommand: String, private val methodType: String, private val params: HashMap<String, String>) {
    companion object {
        private val baseUrl = "http://192.168.0.105/svcourse2017/"
    }

    private val completeUrl = baseUrl + urlCommand

    fun execute(): String {
        var response = StringBuilder()
        try {
            if (checkRequestMethod()) {
                response = if (methodType == "GET") {
                    response.append(makeGetRequest(params))
                } else {
                    response.append(makePostRequest(params))
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return response.toString()
    }

    private fun makeGetRequest(params: HashMap<String, String>): String {
        val jsonResponse = StringBuilder()
        if (params.isEmpty()) {
            val response = URL(completeUrl).readText()
            jsonResponse.append(response)
        } else {
            val response = URL(toLink(params)).readText()
            jsonResponse.append(response)
        }
        return jsonResponse.toString()
    }

    private fun makePostRequest(params: HashMap<String, String>): String {
        val jsonRequest = toJson(params)
        val jsonResponse = StringBuilder()
        val connection = URL(completeUrl).openConnection() as HttpURLConnection

        try {
            connection.apply {
                doOutput = true
                requestMethod = "POST"
                readTimeout = 10000
                connectTimeout = 15000
            }

            val outputStream = connection.outputStream
            val writer = OutputStreamWriter(outputStream, "UTF-8")
            BufferedOutputStream(outputStream).apply {
                writer.append(jsonRequest)
                writer.flush()
                writer.close()
            }
            outputStream.close()

            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                do {
                    jsonResponse.append(reader.readLine())
                } while (reader.readLine() != null)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return jsonResponse.toString()
    }

    private fun toLink(params: HashMap<String, String>): String {
        val linkParams = StringBuilder()
        linkParams.append("?")
        for(entry: Map.Entry<String, String> in params.entries) {
            linkParams.append(entry.key + "=" + entry.value + "&")
        }
        val li = linkParams.lastIndex
        return this.completeUrl + linkParams.deleteCharAt(li)
    }

    private fun toJson(params: HashMap<String, String>): String {
        val jsonBody = JSONObject()
        for (entry: Map.Entry<String, String> in params.entries){
            jsonBody.put(entry.key, entry.value)
        }
        return jsonBody.toString()
    }

    private fun checkRequestMethod() : Boolean{
        val methodArray = arrayListOf("GET", "POST")
        if (methodArray.contains(methodType)){
            return true
        } else {
            throw IllegalStateException()
        }
    }
}
package com.example.core_test

import com.example.core.vo.LCBOItem
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

object MockWebServerResponseUtils {
    fun enqueueServerWithFile(mockWebServer: MockWebServer, fileName: String) {
        val json = this::class.java.getResource("/$fileName")?.readText()
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(json!!))
    }

    fun lcboItemResponseToList(fileName: String): List<LCBOItem> {
        val responseJson = this::class.java.getResource("/$fileName")?.readText()
        return Gson().fromJson(responseJson, Array<LCBOItem>::class.java).toList()
    }
}


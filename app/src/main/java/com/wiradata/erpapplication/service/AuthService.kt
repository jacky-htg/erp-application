package com.wiradata.erpapplication.service

import android.content.Context
import android.util.Log
import com.wiradata.erpapplication.GrpcService
import com.wiradata.erpapplication.users.AuthServiceGrpc
import com.wiradata.erpapplication.users.LoginRequest
import com.wiradata.erpapplication.users.LoginResponse

class AuthService(context: Context) {
    private lateinit var stub: AuthServiceGrpc.AuthServiceBlockingStub

    fun login(user: String, password: String): LoginResponse {
        val chanel = GrpcService().createMangagedChanel()
        stub = AuthServiceGrpc.newBlockingStub(chanel)
        val request =
            LoginRequest.newBuilder().setUsername("wira-admin").setPassword("1234").build()
        var response = stub.login(request)
        Log.i("adit :", response.toString())
        return response
    }
}
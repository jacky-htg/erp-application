package com.wiradata.erpapplication.service

import android.content.Context
import android.util.Log
import com.wiradata.erpapplication.GrpcService
import com.wiradata.erpapplication.users.AuthServiceGrpc
import com.wiradata.erpapplication.users.LoginRequest
import com.wiradata.erpapplication.users.LoginResponse
import java.util.concurrent.TimeUnit

class AuthService(context: Context) {
    private lateinit var stub: AuthServiceGrpc.AuthServiceBlockingStub

    fun login(user: String, password: String): LoginResponse {
        val chanel = GrpcService().createMangagedChanel()
        stub = AuthServiceGrpc.newBlockingStub(chanel)
        val request =
            LoginRequest.newBuilder().setUsername(user).setPassword(password).build()
        var response : LoginResponse = stub.login(request)
        Log.i("adit :", response.toString())
        chanel.awaitTermination(5, TimeUnit.SECONDS);
        return response
    }
}
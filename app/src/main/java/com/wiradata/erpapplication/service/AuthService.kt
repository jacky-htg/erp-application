package com.wiradata.erpapplication.service

import android.content.Context
import android.util.Log
import com.wiradata.erpapplication.GrpcService
import com.wiradata.erpapplication.users.AuthServiceGrpc
import com.wiradata.erpapplication.users.LoginRequest
import com.wiradata.erpapplication.users.LoginResponse
import java.io.IOException
import java.util.concurrent.TimeUnit

class AuthService(context: Context) {
    private lateinit var stub: AuthServiceGrpc.AuthServiceBlockingStub

    fun login(user: String, password: String): LoginResponse {
        var response : LoginResponse = LoginResponse.newBuilder().build()

        try {
            val chanel = GrpcService().createMangagedChanel()
            stub = AuthServiceGrpc.newBlockingStub(chanel)
            val request =
                LoginRequest.newBuilder().setUsername(user).setPassword(password).build()
            response = stub.login(request)
            Log.i("adit :", response.toString())
            chanel.awaitTermination(5, TimeUnit.SECONDS);
            return response
        }
        catch (e : IOException) {
            Log.i("adit :", "io cause exception " + e.cause)
        } catch (e : Exception){
            Log.i("adit :", "cause exception " + e.cause)
        }
        return response
    }
}
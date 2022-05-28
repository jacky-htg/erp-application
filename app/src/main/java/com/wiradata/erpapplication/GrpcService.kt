package com.wiradata.erpapplication

import io.grpc.ManagedChannelBuilder
import java.util.concurrent.Executors

class GrpcService() {
    fun createMangagedChanel() =
        ManagedChannelBuilder.forAddress("api.wira.rijalasepnugroho.com",8001)
            .executor(Executors.newSingleThreadExecutor())
            .usePlaintext()
            .build()
}
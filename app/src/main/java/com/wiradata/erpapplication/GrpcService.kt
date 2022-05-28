package com.wiradata.erpapplication

import io.grpc.ManagedChannelBuilder
import java.util.concurrent.Executors

class GrpcService() {
    fun createMangagedChanel() =
        ManagedChannelBuilder
            .forAddress("168.235.70.115",8001)
            .executor(Executors.newSingleThreadExecutor())
            .usePlaintext()
            .enableRetry()
            .maxRetryAttempts(5)
            .build()
}
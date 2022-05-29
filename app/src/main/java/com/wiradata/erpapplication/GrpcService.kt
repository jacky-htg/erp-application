package com.wiradata.erpapplication

import io.grpc.ManagedChannelBuilder
import java.util.concurrent.Executors

class GrpcService() {
    fun createMangagedChanel() =
        ManagedChannelBuilder
            .forTarget(BuildConfig.SERVER_URL)
            .executor(Executors.newSingleThreadExecutor())
            .usePlaintext()
            .enableRetry()
            .maxRetryAttempts(5)
            .build()
}
package com.kotlinpl.farmacialesson.data.repository

import com.kotlinpl.farmacialesson.data.model.DrugstoreResponse
import com.kotlinpl.farmacialesson.data.network.ApiService
import io.ktor.client.call.body
import javax.inject.Inject

class DrugstoreRepositoryImpl @Inject constructor (
    private val apiService: ApiService,
    // Otras dependencias SQLite
) : DrugstoreRepository {
    override suspend fun getDrugstores(): Result<List<DrugstoreResponse>> {
        return runCatching {
            apiService.getDrugstores().body<List<DrugstoreResponse>>()
        }
    }
}
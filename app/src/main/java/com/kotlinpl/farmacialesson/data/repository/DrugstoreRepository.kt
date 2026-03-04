package com.kotlinpl.farmacialesson.data.repository

import com.kotlinpl.farmacialesson.data.model.DrugstoreResponse

interface DrugstoreRepository {
    suspend fun getDrugstores(): Result<List<DrugstoreResponse>>
}

package com.kotlinpl.farmacialesson.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Drugstore(
    val fecha: String,
    val local_id: String,
    val local_nombre: String,
    val local_lat: Double,
    val local_lng: Double
)

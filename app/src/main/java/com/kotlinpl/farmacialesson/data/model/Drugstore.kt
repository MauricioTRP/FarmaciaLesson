package com.kotlinpl.farmacialesson.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Drugstore(
    val fecha: String,
    val local_id: String,
    val local_nombre: String
)

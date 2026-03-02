package com.kotlinpl.farmacialesson.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDrugstore(
    val data: List<Drugstore>
)

package com.kotlinpl.farmacialesson.data.model

import com.kotlinpl.farmacialesson.domain.Drugstore
import com.kotlinpl.farmacialesson.util.Location
import kotlinx.serialization.Serializable

@Serializable
data class DrugstoreResponse(
    val fecha: String,
    val local_id: String,
    val local_nombre: String,
    val local_lat: String,
    val local_lng: String
){

}

fun DrugstoreResponse.toDrugstore(): Drugstore = Drugstore(
    fecha = this.fecha,
    id = this.local_id,
    name = this.local_nombre,
    location = Location(
        lat = this.local_lat.toDouble(),
        long = this.local_lng.toDouble()
    )
)
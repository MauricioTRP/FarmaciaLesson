package com.kotlinpl.farmacialesson.domain

import com.kotlinpl.farmacialesson.util.Location

data class Drugstore(
    val id: String,
    val fecha: String,
    val name: String,
    val location: Location
)

package com.kotlinpl.farmacialesson.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotlinpl.farmacialesson.data.model.DrugstoreResponse

@Composable
fun Drugstore(
    drugstoreResponse: DrugstoreResponse,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = drugstoreResponse.local_nombre)
        Text(text = drugstoreResponse.fecha)
    }
}
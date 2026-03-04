package com.kotlinpl.farmacialesson.ui.components

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotlinpl.farmacialesson.data.model.DrugstoreResponse

@Composable
fun DrugstoreList(
    drugstoreResponses: List<DrugstoreResponse>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(drugstoreResponses.size) { index ->
            Drugstore(drugstoreResponse = drugstoreResponses[index])
        }
    }

    Log.d("DrugstoreList", "Drugstores: $drugstoreResponses")
}

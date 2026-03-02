package com.kotlinpl.farmacialesson.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotlinpl.farmacialesson.R
import com.kotlinpl.farmacialesson.view_model.DrugstoreViewModel
import com.kotlinpl.farmacialesson.view_model.DrugstoresErrors

@Composable
fun DrugstoreScreen(
    modifier: Modifier = Modifier,
    viewModel: DrugstoreViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    if(uiState.value.isLoading) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoadingIndicator()
        }
    }

    if(uiState.value.error != null) {
        when(uiState.value.error) {
            DrugstoresErrors.SerializationError -> Text(text = stringResource(R.string.serialization_error))
            DrugstoresErrors.UnknownError -> Text(text = stringResource(R.string.unknown_error))
            else -> Text(text = stringResource(R.string.unknown_error))
        }

        Log.e("DrugstoreScreen", "Error: ${uiState.value.error}")
    } else {
        DrugstoreList(
            drugstores = uiState.value.drugstores,
            modifier = modifier
        )
    }

}
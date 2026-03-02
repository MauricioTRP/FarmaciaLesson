package com.kotlinpl.farmacialesson.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.farmacialesson.data.repository.DrugstoreRepository
import com.kotlinpl.farmacialesson.data.repository.DrugstoreRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException

class DrugstoreViewModel(
    private val repository: DrugstoreRepository = DrugstoreRepositoryImpl(),
) : ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    init {
        getDrugstores()
    }

    private fun getDrugstores() {
        viewModelScope.launch {
            try {
                val result = repository.getDrugstores()

                if (result.isSuccess) {
                    // El repo pudo traer la info como corresponde
                    val drugstores = result.getOrThrow()

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        drugstores = drugstores,
                        error = null
                    )
                } else {
                    throw result.exceptionOrNull() ?: SerializationException("Serialization Error")
                }
            } catch (e: SerializationException) {
                    val errorMessage = e.message
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        drugstores = emptyList(),
                        error = DrugstoresErrors.SerializationError
                    )
                    Log.e("DrugstoreViewModel", "Error fetching drugstores: $errorMessage")
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    drugstores = emptyList(),
                    error = DrugstoresErrors.UnknownError
                )

                Log.e("DrugstoreViewModel", "Error fetching drugstores", e)
            }
        }
    }
}

enum class DrugstoresErrors {
    SerializationError,
    UnknownError
}

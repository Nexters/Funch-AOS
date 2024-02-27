package com.moya.funch.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.Mbti
import com.moya.funch.usecase.LoadMbtiCollectionUseCase
import com.moya.funch.usecase.LoadUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

internal data class CollectionUiState(
    val name: String = "",
    val mbtiCollection: List<Mbti> = emptyList()
)

@HiltViewModel
internal class CollectionViewModel @Inject constructor(
    private val loadMbtiCollectionUseCase: LoadMbtiCollectionUseCase,
    private val loadUserProfileUseCase: LoadUserProfileUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(CollectionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadMbtiCollection()
    }

    private fun loadMbtiCollection() {
        viewModelScope.launch {
            loadUserProfileUseCase().onSuccess {
                _uiState.value = _uiState.value.copy(name = it.name)
                loadMbtiCollectionUseCase().collect { mbtiCollection ->
                    _uiState.value = _uiState.value.copy(mbtiCollection = mbtiCollection)
                }
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }
}

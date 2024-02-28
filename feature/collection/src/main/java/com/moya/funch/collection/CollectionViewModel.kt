package com.moya.funch.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.Mbti
import com.moya.funch.usecase.LoadMbtiCollectionUseCase
import com.moya.funch.usecase.LoadUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

internal data class CollectionUiState(
    val name: String = "",
    val mbtiCollection: List<Mbti> = emptyList()
)

@HiltViewModel
internal class CollectionViewModel @Inject constructor(
    private val loadMbtiCollectionUseCase: LoadMbtiCollectionUseCase,
    private val loadUserProfileUseCase: LoadUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CollectionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadUserProfile()
        loadMbtiCollection()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            loadUserProfileUseCase().onSuccess { profile ->
                _uiState.value = _uiState.value.copy(name = profile.name)
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }

    private fun loadMbtiCollection() {
        viewModelScope.launch {
            loadMbtiCollectionUseCase().onSuccess { mbtiCollection ->
                _uiState.value = _uiState.value.copy(mbtiCollection = mbtiCollection)
            }.onFailure {
                Timber.e(it.message)
            }
        }
    }
}

package com.moya.funch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.profile.Profile
import com.moya.funch.usecase.LoadUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class MyProfileUiState {
    data object Loading : MyProfileUiState()
    data class Success(val profile: Profile) : MyProfileUiState()
    data class Error(val message: String) : MyProfileUiState()
}

@HiltViewModel
internal class MyProfileViewModel @Inject constructor(
    private val loadUserProfileUseCase: LoadUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyProfileUiState>(MyProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val result = loadUserProfileUseCase()
            result.onSuccess { profile ->
                _uiState.value = MyProfileUiState.Success(profile)
            }.onFailure { exception ->
                _uiState.value = MyProfileUiState.Error(exception.message ?: "Unknown Error")
            }
        }
    }
}

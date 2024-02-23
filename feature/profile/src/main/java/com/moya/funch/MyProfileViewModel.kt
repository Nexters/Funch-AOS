package com.moya.funch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.entity.profile.Profile
import com.moya.funch.usecase.DeleteUserProfileUseCase
import com.moya.funch.usecase.LoadUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

sealed class MyProfileUiState {
    data object Loading : MyProfileUiState()
    data class Success(val profile: Profile) : MyProfileUiState()
    data object Error : MyProfileUiState()
}

sealed class MyProfileEvent {
    data object DeleteProfile : MyProfileEvent()
}

@HiltViewModel
internal class MyProfileViewModel @Inject constructor(
    private val loadUserProfileUseCase: LoadUserProfileUseCase,
    private val deleteUserProfileUseCase: DeleteUserProfileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyProfileUiState>(MyProfileUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<MyProfileEvent>()
    val event = _event.asSharedFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            val result = loadUserProfileUseCase()
            result.onSuccess { profile ->
                _uiState.value = MyProfileUiState.Success(profile)
            }.onFailure { exception ->
                _uiState.value = MyProfileUiState.Error
                Timber.e(exception)
            }
        }
    }

    fun deleteUserProfile() {
        viewModelScope.launch {
            deleteUserProfileUseCase().onSuccess {
                Timber.d(it)
                _event.emit(MyProfileEvent.DeleteProfile)
            }.onFailure { exception ->
                Timber.e(exception)
            }
        }
    }
}

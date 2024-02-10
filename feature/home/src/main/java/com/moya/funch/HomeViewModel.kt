package com.moya.funch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moya.funch.usecase.CanMatchProfileUseCase
import com.moya.funch.usecase.LoadUserProfileUseCase
import com.moya.funch.usecase.LoadViewCountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

data class HomeModel(
    val myCode: String,
    val viewCount: Int,
    val matchingCode: String = ""
) {
    companion object {
        fun empty() = HomeModel(
            myCode = "",
            viewCount = 0,
            matchingCode = ""
        )
    }
}

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val canMatchProfileUse: CanMatchProfileUseCase,
    private val loadUserProfileUseCase: LoadUserProfileUseCase,
    private val loadViewCountUseCase: LoadViewCountUseCase
) : ViewModel() {
    private val _homeModel = MutableStateFlow(HomeModel.empty())
    val homeModel = _homeModel.asStateFlow()

    private val _homeErrorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val homeErrorMessage = _homeErrorMessage.asSharedFlow()

    private val _matched = MutableStateFlow(false)
    val matched = _matched.asStateFlow()

    init {
        initHome()
    }

    fun setMatchingCode(code: String) {
        _homeModel.value = _homeModel.value.copy(
            matchingCode = code.uppercase()
        )
    }

    fun matchProfile() {
        viewModelScope.launch {
            canMatchProfileUse(homeModel.value.matchingCode).onSuccess {
                _matched.value = true
            }.onFailure {
                Timber.e("matchProfile(): ${it.stackTraceToString()}")
                // TODO @murjune : Matching Page로 일단 갈 수 있도록 해둠!
                _homeModel.value = _homeModel.value.copy(matchingCode = "TEMP")
                _matched.value = true
                // TODO 여기까지 삭제
                _homeErrorMessage.emit("매칭할 수 없는 코드입니다.")
            }
        }
    }

    fun matchDone() {
        _matched.value = false
    }

    private fun initHome() {
        fetchUserProfile()
        fetchViewCount()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            loadUserProfileUseCase().onSuccess {
                setMyCode(it.code)
            }.onFailure {
                Timber.e("fetchUserProfile(): ${it.stackTraceToString()}")
                setMyCode("NONE")
            }
        }
    }

    private fun fetchViewCount() {
        viewModelScope.launch {
            loadViewCountUseCase().onSuccess {
                setViewCount(it)
            }.onFailure {
                Timber.e("fetchViewCount(): ${it.stackTraceToString()}")
                setViewCount(0)
            }
        }
    }

    private fun setMyCode(code: String) {
        _homeModel.value = _homeModel.value.copy(
            myCode = code.uppercase()
        )
    }

    private fun setViewCount(count: Int) {
        _homeModel.value = _homeModel.value.copy(
            viewCount = count
        )
    }
}

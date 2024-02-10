package com.moya.funch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

// @Gun Hyung TODO : 모델 모듈로 분리
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
//    private val canMatchProfileUse: CanMatchProfileUseCase,
) : ViewModel() {
    private val _homeModel = MutableStateFlow(HomeModel.empty()) // @Gun Hyung TODO : 모델 초기화 필요
    val homeModel = _homeModel.asStateFlow()

    private val _homeErrorMessage: MutableSharedFlow<String> = MutableSharedFlow()
    val homeErrorMessage = _homeErrorMessage.asSharedFlow()

    private val _matched = MutableStateFlow(false)
    val matched = _matched.asStateFlow()

    init {
        initHome()
    }

    fun setMatchingCode(code: String) {
        Timber.e("setMatchingCode: $code")
        _homeModel.value = _homeModel.value.copy(
            matchingCode = code.uppercase()
        )
    }

    fun matchProfile() {
        viewModelScope.launch {
            // @murjune TODO : canMatchProfileUse(userId, targetCode) 로 대체
//            if (canMatchProfileUse(userId, targetCode)) {
//            _homeModel.value = _homeModel.value.copy(canMatched = true)
//            } else {
//                _homeErrorMessage.emit("매칭할 수 없는 코드입니다.")
//            }
            if (homeModel.value.matchingCode.isBlank()) {
                _homeErrorMessage.emit("존재하지 않는 사용자입니다")
            } else {
                delay(1000L)
                _matched.emit(true)
            }
        }
    }

    fun matchDone() {
        _matched.value = false
    }

    private fun initHome() { // @Gun Hyung TODO : 도메인 완성시 init 함수 제작
        setMyCode("u23c")
        setViewCount(12)
    }

    private fun setMyCode(code: String) { // @Gun Hyung TODO : 도메인에서 .uppercase() 처리
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

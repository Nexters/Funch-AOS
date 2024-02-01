package com.moya.funch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// @Gun Hyung TODO : 모델 모듈로 분리
data class HomeModel(
    val myCode: String,
    val viewCount: Int,
    val matchingCode: String,
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
    // @Gun Hyung TODO : UseCase 추가
) : ViewModel() {
    private val _homeModel = MutableStateFlow(HomeModel.empty()) // @Gun Hyung TODO : 모델 초기화 필요
    val homeModel = _homeModel.asStateFlow()

    init {
        initHome()
    }

    private fun initHome() { // @Gun Hyung TODO : 도메인 완성시 init 함수 제작
        setMyCode("u23c")
        setViewCount(12)
    }

    fun setMyCode(code: String) {
        _homeModel.value =
            _homeModel.value.copy(
                myCode = code.uppercase() // @Gun Hyung TODO : 도메인에서 .uppercase() 처리
            )
    }

    fun setViewCount(count: Int) {
        _homeModel.value =
            _homeModel.value.copy(
                viewCount = count
            )
    }

    fun setMatchingCode(code: String) {
        _homeModel.value =
            _homeModel.value.copy(
                matchingCode = code.uppercase()
            )
    }
}

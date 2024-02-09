package com.moya.funch

import androidx.lifecycle.ViewModel
import com.moya.funch.entity.profile.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
internal class MyProfileViewModel @Inject constructor() : ViewModel() {
    private val _profile = MutableStateFlow(Profile.default())
    val profile = _profile.asStateFlow()
}

package com.moya.funch

import androidx.lifecycle.ViewModel
import com.moya.funch.ui.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
internal class MyProfileViewModel @Inject constructor(

) : ViewModel() {
    private val _profile = MutableStateFlow(Profile.default())
    val profile = _profile.asStateFlow()

}
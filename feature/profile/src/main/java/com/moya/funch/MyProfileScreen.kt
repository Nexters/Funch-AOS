package com.moya.funch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moya.funch.common.clubPainter
import com.moya.funch.common.jobPainter
import com.moya.funch.common.subwayLinePainter
import com.moya.funch.component.FunchChip
import com.moya.funch.component.FunchIcon
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.Mbti
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.entity.profile.Profile
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.profile.R
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.theme.White
import com.moya.funch.ui.FunchDialog
import com.moya.funch.ui.FunchTopBar
import com.moya.funch.uimodel.ProfileLabel

@Composable
internal fun MyProfileRoute(
    viewModel: MyProfileViewModel = hiltViewModel(),
    onCloseMyProfile: () -> Unit,
    onNavigateCreateProfile: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            if (event is MyProfileEvent.DeleteProfile) {
                onNavigateCreateProfile()
            }
        }
    }

    MyProfileScreen(
        uiState = uiState,
        onCloseMyProfile = onCloseMyProfile,
        onDeleteProfile = viewModel::deleteUserProfile
    )
}

@Composable
internal fun MyProfileScreen(uiState: MyProfileUiState, onCloseMyProfile: () -> Unit, onDeleteProfile: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FunchTopBar(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 20.dp
                ),
            enabledLeadingIcon = true,
            enabledTrailingIcon = true,
            leadingIcon = FunchIcon(
                resId = FunchIconAsset.Arrow.arrow_left_small_24,
                description = "Back",
                tint = Gray400
            ),
            onClickLeadingIcon = onCloseMyProfile
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(320.dp)
                    .heightIn(min = 477.dp)
                    .clip(FunchTheme.shapes.large)
                    .background(
                        color = Gray800,
                        shape = FunchTheme.shapes.large
                    )
                    .padding(
                        vertical = 24.dp,
                        horizontal = 20.dp
                    )
            ) {
                when (uiState) {
                    is MyProfileUiState.Loading -> {
                        LoadingContent()
                    }

                    is MyProfileUiState.Success -> {
                        LoadMyProfile(profile = uiState.profile)
                    }

                    is MyProfileUiState.Error -> {
                        ErrorContent()
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = Gray800,
                        shape = FunchTheme.shapes.small
                    )
                    .clip(FunchTheme.shapes.small)
                    .clickable(
                        onClick = { showDialog = true }
                    )
                    .padding(
                        vertical = 7.5f.dp,
                        horizontal = 12.dp
                    )
            ) {
                Text(
                    text = stringResource(id = R.string.profile_delete_body),
                    style = FunchTheme.typography.b,
                    color = White
                )
            }
        }
    }
    if (showDialog) {
        FunchDialog(
            title = stringResource(id = R.string.profile_delete_body),
            text = stringResource(id = R.string.dialog_text),
            dismissText = stringResource(id = R.string.dialog_dismiss),
            confirmText = stringResource(id = R.string.dialog_confirm),
            onDismiss = { showDialog = false },
            onConfirm = onDeleteProfile
        )
    }
}

@Composable
private fun LoadMyProfile(profile: Profile) {
    Text(
        text = profile.code,
        style = FunchTheme.typography.b,
        color = Gray400
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = profile.name,
        style = FunchTheme.typography.t2,
        color = Color.White
    )
    Spacer(modifier = Modifier.height(20.dp))
    UsersDistinct(profile = profile)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun UsersDistinct(profile: Profile) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileLabel.entries.filterNot { it == ProfileLabel.NICKNAME }.forEach { profileLabel ->
            val labelValues = when (profileLabel) {
                ProfileLabel.JOB -> listOf(profile.job.krName)
                ProfileLabel.CLUB -> profile.clubs.map { it.label }
                ProfileLabel.MBTI -> listOf(profile.mbti.name)
                ProfileLabel.BLOOD_TYPE -> listOf(profile.blood.type)
                ProfileLabel.SUBWAY -> profile.subways.map { it.name }
                ProfileLabel.NICKNAME -> emptyList()
            }

            if (labelValues.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .width(52.dp)
                            .height(48.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = profileLabel.labelName,
                            color = Gray400,
                            style = FunchTheme.typography.b
                        )
                    }
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        labelValues.forEach { value ->
                            val leadingIcon = when (profileLabel) {
                                ProfileLabel.JOB -> jobPainter(value)
                                ProfileLabel.CLUB -> clubPainter(value)
                                else -> null
                            }
                            val trailingIcon = when (profileLabel) {
                                ProfileLabel.SUBWAY ->
                                    profile.subways.find { it.name == value }?.lines?.map {
                                        subwayLinePainter(it.name)
                                    }

                                else -> null
                            }

                            FunchChip(
                                leadingIcon = leadingIcon?.let { icon ->
                                    {
                                        Box(
                                            modifier = Modifier
                                                .size(32.dp)
                                                .background(
                                                    color = Gray900,
                                                    shape = FunchTheme.shapes.extraSmall
                                                )
                                                .clip(FunchTheme.shapes.extraSmall),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Image(
                                                modifier = Modifier.size(18.dp),
                                                painter = icon,
                                                contentDescription = ""
                                            )
                                        }
                                    }
                                },
                                selected = true,
                                enabled = false,
                                label = {
                                    Text(
                                        text = if (profileLabel == ProfileLabel.SUBWAY) value + "역" else value,
                                        style = FunchTheme.typography.b,
                                        color = White
                                    )
                                },
                                trailingIcon = trailingIcon?.let { trailingIcons ->
                                    {
                                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                            trailingIcons.forEach { icon ->
                                                Image(
                                                    painter = icon,
                                                    contentDescription = ""
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Loading...",
            color = White
        )
    }
}

@Composable
private fun ErrorContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Error",
            color = White
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.NEXUS_6
)
@Composable
private fun Preview1() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            MyProfileScreen(
                uiState = MyProfileUiState.Success(
                    profile = Profile(
                        id = "QW2E213EEADF",
                        code = "U23C",
                        name = "김민수",
                        job = Job.DEVELOPER,
                        clubs = listOf(Club.NEXTERS, Club.SOPT, Club.DEPROMEET),
                        mbti = Mbti.ENFP,
                        blood = Blood.A,
                        subways = listOf(
                            SubwayStation(
                                "동대문역사문화공원",
                                listOf(
                                    SubwayLine.ONE,
                                    SubwayLine.FOUR
                                )
                            ),
                            SubwayStation(
                                "초지역",
                                listOf(
                                    SubwayLine.TWO,
                                    SubwayLine.THREE
                                )
                            )
                        )
                    )
                ),
                onCloseMyProfile = {},
                onDeleteProfile = {}
            )
        }
    }
}

@Preview(
    name = "My Profile Loading",
    showBackground = true,
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
private fun Preview2() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            MyProfileScreen(
                uiState = MyProfileUiState.Loading,
                onCloseMyProfile = {},
                onDeleteProfile = {}
            )
        }
    }
}

@Preview(
    name = "My Profile Loading",
    showBackground = true,
    device = Devices.NEXUS_6,
    showSystemUi = true
)
@Composable
private fun Preview3() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            MyProfileScreen(
                uiState = MyProfileUiState.Error,
                onCloseMyProfile = {},
                onDeleteProfile = {}
            )
        }
    }
}

package com.moya.funch

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moya.funch.common.clubPainter
import com.moya.funch.common.jobPainter
import com.moya.funch.component.FunchButtonType
import com.moya.funch.component.FunchChip
import com.moya.funch.component.FunchIcon
import com.moya.funch.component.FunchIconTextField
import com.moya.funch.component.FunchLargeLabel
import com.moya.funch.component.FunchMainButton
import com.moya.funch.component.FunchMaxLengthTextField
import com.moya.funch.component.FunchSmallLabel
import com.moya.funch.entity.Blood
import com.moya.funch.entity.Club
import com.moya.funch.entity.Job
import com.moya.funch.entity.SubwayLine
import com.moya.funch.entity.SubwayStation
import com.moya.funch.icon.FunchIconAsset
import com.moya.funch.profile.R
import com.moya.funch.theme.FunchTheme
import com.moya.funch.theme.Gray300
import com.moya.funch.theme.Gray400
import com.moya.funch.theme.Gray500
import com.moya.funch.theme.Gray800
import com.moya.funch.theme.Gray900
import com.moya.funch.theme.LocalBackgroundTheme
import com.moya.funch.theme.White
import com.moya.funch.ui.FunchDropDownButton
import com.moya.funch.ui.FunchDropDownMenu
import com.moya.funch.ui.FunchErrorCaption
import com.moya.funch.ui.FunchTopBar
import com.moya.funch.uimodel.MbtiItem
import com.moya.funch.uimodel.ProfileLabel
import com.moya.funch.uimodel.ProfileUiModel
import com.moya.funch.uimodel.SubwayTextFieldState

@Composable
internal fun CreateProfileRoute(onNavigateToHome: () -> Unit, viewModel: CreateProfileViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is CreateProfileEvent.NavigateToHome -> {
                    onNavigateToHome()
                }

                is CreateProfileEvent.ShowError -> {
                    // @Gun Hyung TODO : 에러 메시지 호출
                }
            }
        }
    }

    CreateProfileScreen(
        profile = uiState.profile,
        isCreateProfile = uiState.profile.isButtonEnabled,
        onSelectJob = viewModel::setJob,
        onSelectClub = viewModel::setClub,
        onSelectMbti = viewModel::setMbti,
        onSelectBloodType = viewModel::setBloodType,
        onNicknameChange = viewModel::setNickname,
        onSubwayStationChange = viewModel::setSubwayName,
        onCreateProfile = viewModel::createProfile,
        onSendFeedback = {}
    )

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = { },
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ),
            contentAlignment = Alignment.Center
        ) {
            // @Gun Hyung TODO : 로딩 UI 디자인시스템에 정의하고 그리기
        }
    }
}

@Composable
fun CreateProfileScreen(
    profile: ProfileUiModel,
    isCreateProfile: Boolean,
    onSelectJob: (Job) -> Unit,
    onSelectClub: (Club) -> Unit,
    onSelectMbti: (MbtiItem) -> Unit,
    onSelectBloodType: (Blood) -> Unit,
    onNicknameChange: (String) -> Unit,
    onSubwayStationChange: (String) -> Unit,
    onCreateProfile: () -> Unit,
    onSendFeedback: () -> Unit
) {
    val scrollState = rememberScrollState()
    val backgroundColor = LocalBackgroundTheme.current.color
    var isKeyboardVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            FunchTopBar(
                modifier = Modifier.padding(end = 20.dp),
                leadingIcon = null,
                onClickTrailingIcon = onSendFeedback
            )
        },
        bottomBar = {
            if (!isKeyboardVisible) {
                BottomBar(
                    backgroundColor = backgroundColor,
                    isCreateProfile = isCreateProfile,
                    onCreateProfile = onCreateProfile
                )
            }
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .fillMaxSize()
                .verticalScroll(state = scrollState)
                .padding(padding)
        ) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.create_profile_title),
                    color = White,
                    style = FunchTheme.typography.t2
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = stringResource(id = R.string.create_profile_sub_title),
                    color = Gray300,
                    style = FunchTheme.typography.b
                )
                Spacer(modifier = Modifier.height(24.dp))
                NicknameRow(
                    nickname = profile.name,
                    onNicknameChange = onNicknameChange,
                    isKeyboardVisible = { isKeyboardVisible = it }
                )
                Spacer(modifier = Modifier.height(14.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    JobRow(profile = profile, onSelected = onSelectJob)
                    ClubRow(onSelectClub = onSelectClub)
                    MbtiRow(profile = profile, onSelectMbti = onSelectMbti)
                    BooldTypeRow(onSelectBloodType = onSelectBloodType)
                    SubwayRow(
                        subwayStation = profile.subway,
                        onSubwayStationChange = onSubwayStationChange,
                        isKeyboardVisible = { isKeyboardVisible = it },
                        textFieldState = profile.subwayTextFieldState,
                        subwayStations = profile.subwayStations
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            if (isKeyboardVisible) {
                BottomBar(
                    backgroundColor = backgroundColor,
                    isCreateProfile = isCreateProfile,
                    onCreateProfile = onCreateProfile
                )
            }
        }
    }
}

private const val MAX_NICKNAME_LENGTH = 9

@Composable
private fun NicknameRow(nickname: String, onNicknameChange: (String) -> Unit, isKeyboardVisible: (Boolean) -> Unit) {
    var isNicknameError by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isFocused) {
        if (!isFocused || nickname.length < MAX_NICKNAME_LENGTH) {
            isNicknameError = false
        }
        isKeyboardVisible(isFocused)
    }

    Row {
        FunchLargeLabel(text = ProfileLabel.NICKNAME.labelName)
        Column {
            FunchMaxLengthTextField(
                value = nickname,
                onValueChange = { innerText ->
                    isNicknameError = if (innerText.length <= MAX_NICKNAME_LENGTH) {
                        onNicknameChange(innerText)
                        false
                    } else {
                        true
                    }
                },
                maxLength = MAX_NICKNAME_LENGTH,
                hint = stringResource(id = R.string.nickname_textfield_hint, MAX_NICKNAME_LENGTH),
                isError = isNicknameError,
                interactionSource = interactionSource,
                isFocus = isFocused,
                errorText = stringResource(id = R.string.nickname_error_caption, MAX_NICKNAME_LENGTH),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun JobRow(profile: ProfileUiModel, onSelected: (Job) -> Unit) {
    Row {
        FunchSmallLabel(text = ProfileLabel.JOB.labelName)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Job.entries.filterNot { it == Job.IDLE }.forEach { job ->
                FunchChip(
                    selected = profile.job == job,
                    enabled = true,
                    onSelected = { onSelected(job) },
                    label = {
                        Text(
                            text = job.krName,
                            style = FunchTheme.typography.b,
                            color = White
                        )
                    },
                    leadingIcon = {
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
                                painter = jobPainter(job.krName),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ClubRow(onSelectClub: (Club) -> Unit) {
    Row {
        FunchSmallLabel(text = ProfileLabel.CLUB.labelName)
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Club.entries.filterNot { it == Club.IDLE }.forEach { club ->
                var isSelected by remember { mutableStateOf(false) }
                FunchChip(
                    selected = isSelected,
                    enabled = true,
                    onSelected = {
                        onSelectClub(club)
                        isSelected = !isSelected
                    },
                    label = {
                        Text(
                            text = club.label,
                            style = FunchTheme.typography.b,
                            color = White
                        )
                    },
                    leadingIcon = {
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
                                painter = clubPainter(club.label),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun MbtiRow(profile: ProfileUiModel, onSelectMbti: (MbtiItem) -> Unit) {
    val eOrI = profile.eOrI
    val nOrS = profile.nOrS
    val tOrF = profile.tOrF
    val jOrP = profile.jOrP
    val currentMbti = listOf(eOrI, nOrS, tOrF, jOrP)

    Row {
        FunchSmallLabel(text = ProfileLabel.MBTI.labelName)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MbtiItem.entries.chunked(2).forEachIndexed { i, pair ->
                Column(
                    modifier = Modifier
                        .background(color = Gray800, shape = FunchTheme.shapes.medium)
                        .clip(FunchTheme.shapes.medium)
                ) {
                    pair.forEach { mbti ->
                        MbtiButton(
                            mbtiItem = mbti,
                            isSelected = currentMbti[i] == mbti,
                            onSelected = {
                                onSelectMbti(it)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MbtiButton(mbtiItem: MbtiItem, isSelected: Boolean, onSelected: (MbtiItem) -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                color = if (isSelected) Gray500 else Color.Transparent,
                shape = FunchTheme.shapes.medium
            )
            .clip(FunchTheme.shapes.medium)
            .clickable(
                onClick = { onSelected(mbtiItem) },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = mbtiItem.name,
            color = if (isSelected) White else Gray400,
            style = FunchTheme.typography.sbt2
        )
    }
}

@Composable
private fun BooldTypeRow(onSelectBloodType: (Blood) -> Unit) {
    val bloodTypes = Blood.entries.filterNot { it == Blood.IDLE }.map { it.type }
    var placeHolder by remember { mutableStateOf(bloodTypes[0]) }
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    val buttonBounds = remember { mutableStateOf(Rect.Zero) }

    Row {
        FunchLargeLabel(text = ProfileLabel.BLOOD_TYPE.labelName)
        Box {
            FunchDropDownButton(
                placeHolder = placeHolder,
                onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
                isDropDownMenuExpanded = isDropDownMenuExpanded,
                indication = null,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    buttonBounds.value = coordinates.boundsInWindow()
                }
            )
            if (isDropDownMenuExpanded) {
                FunchDropDownMenu(
                    items = bloodTypes,
                    buttonBounds = buttonBounds.value,
                    onItemSelected = { bloodType ->
                        onSelectBloodType(Blood.of(bloodType))
                        placeHolder = bloodType
                        isDropDownMenuExpanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun SubwayRow(
    subwayStation: String,
    onSubwayStationChange: (String) -> Unit,
    isKeyboardVisible: (Boolean) -> Unit,
    textFieldState: SubwayTextFieldState,
    subwayStations: List<SubwayStation>
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isFocused) {
        isKeyboardVisible(isFocused)
    }

    Row {
        FunchLargeLabel(text = ProfileLabel.SUBWAY.labelName)
        Column(modifier = Modifier.height(97.dp)) {
            FunchIconTextField(
                value = subwayStation,
                onValueChange = onSubwayStationChange,
                hint = stringResource(id = R.string.subway_textfield_hint),
                isError = textFieldState == SubwayTextFieldState.Error,
                iconType = FunchIcon(
                    resId = FunchIconAsset.Search.search_24,
                    tint = Gray400,
                    description = ""
                ),
                interactionSource = interactionSource,
                isFocus = isFocused,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )

            when (textFieldState) {
                is SubwayTextFieldState.Empty -> { }
                is SubwayTextFieldState.Error -> {
                    FunchErrorCaption(
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 4.dp
                            ),
                        errorText = stringResource(id = R.string.subway_error_caption)
                    )
                }

                is SubwayTextFieldState.Success -> {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        subwayStations.forEach { station ->
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Gray800,
                                        shape = FunchTheme.shapes.extraLarge
                                    )
                                    .clip(FunchTheme.shapes.extraLarge)
                                    .clickable(
                                        onClick = {
                                            onSubwayStationChange(station.name)
                                            focusManager.clearFocus()
                                        },
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    )
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = station.name,
                                    color = White,
                                    style = FunchTheme.typography.b
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomBar(backgroundColor: Color, isCreateProfile: Boolean, onCreateProfile: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(
                top = 16.dp,
                start = 20.dp,
                end = 20.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        FunchMainButton(
            enabled = isCreateProfile,
            modifier = Modifier.fillMaxWidth(),
            buttonType = FunchButtonType.Full,
            onClick = onCreateProfile,
            text = stringResource(id = R.string.bottom_button_title)
        )
    }
}

@Preview(
    showBackground = true,
    name = "CreateProfileScreen",
    device = "id:pixel_7_pro"
)
@Composable
private fun Preview1() {
    FunchTheme {
        val backgroundColor = LocalBackgroundTheme.current.color

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            CreateProfileScreen(
                profile = ProfileUiModel(),
                isCreateProfile = false,
                onSelectJob = {},
                onSelectClub = {},
                onSelectMbti = {},
                onSelectBloodType = {},
                onNicknameChange = {},
                onSubwayStationChange = {},
                onCreateProfile = {},
                onSendFeedback = {}
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "CreateProfileScreen",
    device = "id:pixel_7_pro"
)
@Composable
private fun Preview2() {
    FunchTheme {
        var text by remember { mutableStateOf("") }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LocalBackgroundTheme.current.color
        ) {
            SubwayRow(
                subwayStation = text,
                onSubwayStationChange = { text = it },
                isKeyboardVisible = {},
                textFieldState = SubwayTextFieldState.Success,
                subwayStations = listOf(
                    SubwayStation("강남역"),
                    SubwayStation("역삼역"),
                    SubwayStation("삼성역"),
                    SubwayStation("선릉역"),
                    SubwayStation("삼성중앙역"),
                    SubwayStation("봉은사역"),
                    SubwayStation("삼전")
                )
            )
        }
    }
}

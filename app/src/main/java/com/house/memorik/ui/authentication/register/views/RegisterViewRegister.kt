package com.house.memorik.ui.authentication.register.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.house.memorik.R
import com.house.memorik.ui.authentication.register.models.AuthenticationSection
import com.house.memorik.ui.theme.BottomEllipseShape
import com.house.memorik.ui.theme.MemorikTheme
import com.house.memorik.ui.theme.White
import com.house.memorik.utils.ext.AnimatedFadingVisibilityWithDelay

@Composable
fun RegisterViewRegister(
    offsetY: Dp = (-290).dp,
    section: AuthenticationSection = AuthenticationSection.REGISTER,
    scaleRegisterText: Float = 1f,
    email: String = "",
    username: String = "",
    password: String = "",
    onEmailChanged: (String) -> Unit,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onStateChanged: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        AuthenticationScaledImage(offsetY = offsetY)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthenticationTittleText(
                text = stringResource(id = R.string.authentication_screen_sign_up),
                scale = scaleRegisterText
            ) {
                onStateChanged()
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = offsetY)
                    .padding(horizontal = 30.dp)
            ) {
                AnimatedFadingVisibilityWithDelay(
                    visible = section == AuthenticationSection.REGISTER,
                    enterDelay = emailTextFieldEnterDelay,
                    exitDelay = emailTextFieldExitDelay,
                    exitDuration = quickExitDuration
                ) {
                    AuthenticationTextField(
                        hint = stringResource(id = R.string.authentication_screen_email),
                        backGroundColor = White,
                        text = email
                    ) {
                        onEmailChanged(it)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                AnimatedFadingVisibilityWithDelay(
                    visible = section == AuthenticationSection.REGISTER,
                    enterDelay = usernameTextFieldEnterDelay,
                    exitDelay = usernameTextFieldExitDelay,
                    exitDuration = quickExitDuration
                ) {
                    AuthenticationTextField(
                        hint = stringResource(id = R.string.authentication_screen_username),
                        backGroundColor = White,
                        text = username
                    ) {
                        onUsernameChanged(it)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                AnimatedFadingVisibilityWithDelay(
                    visible = section == AuthenticationSection.REGISTER,
                    enterDelay = passwordTextFieldEnterDelay,
                    exitDelay = passwordTextFieldExitDelay,
                    exitDuration = mediumExitDuration
                ) {
                    AuthenticationTextField(
                        hint = stringResource(id = R.string.authentication_screen_password),
                        backGroundColor = White,
                        text = password
                    ) {
                        onPasswordChanged(it)
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
                AnimatedFadingVisibilityWithDelay(
                    visible = section == AuthenticationSection.REGISTER,
                    enterDelay = signUpButtonEnterDelay,
                    exitDelay = signUpButtonExitDelay,
                    exitDuration = mediumExitDuration,
                ) {
                    AuthenticationButton(text = stringResource(id = R.string.authentication_screen_sign_up)) {

                    }
                }
            }
        }
    }
}

@Composable
private fun AuthenticationScaledImage(offsetY: Dp) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = offsetY)
            .graphicsLayer {
                shape = BottomEllipseShape()
                clip = true
            },
        painter = painterResource(id = R.drawable.image_friends),
        contentDescription = null,
        alignment = Alignment.TopCenter,
        contentScale = ContentScale.Crop,
    )
}

@Composable
@Preview
private fun RegisterSectionPreview() {
    MemorikTheme() {
        RegisterViewRegister(
            onEmailChanged = {},
            onUsernameChanged = {},
            onPasswordChanged = {},
            onStateChanged = {}
        )
    }
}

private const val emailTextFieldEnterDelay = 500
private const val usernameTextFieldEnterDelay = 400
private const val passwordTextFieldEnterDelay = 300
private const val signUpButtonEnterDelay = 200

private const val emailTextFieldExitDelay = 50
private const val usernameTextFieldExitDelay = 100
private const val passwordTextFieldExitDelay = 150
private const val signUpButtonExitDelay = 200

private const val quickExitDuration = 100
private const val mediumExitDuration = 200
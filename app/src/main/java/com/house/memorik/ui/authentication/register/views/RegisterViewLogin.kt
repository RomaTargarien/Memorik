package com.house.memorik.ui.authentication.register.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house.memorik.R
import com.house.memorik.ui.authentication.register.models.AuthenticationSection
import com.house.memorik.ui.theme.MemorikTheme
import com.house.memorik.ui.theme.Orange
import com.house.memorik.utils.ext.AnimatedFadingVisibilityWithDelay

@Composable
fun RegisterViewLogin(
    offsetY: Dp = 0.dp,
    scaleLogInText: Float = 1f,
    registerState: AuthenticationSection = AuthenticationSection.LOG_IN,
    username: String = "",
    password: String = "",
    onUsernameTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    onStateChanged: () -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onLoginButtonCLicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .offset(y = offsetY)
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthenticationTittleText(
            modifier = Modifier.offset(y = -20.dp),
            text = stringResource(id = R.string.authentication_screen_log_in),
            scale = scaleLogInText,
            onClick = {
                onStateChanged()
            }
        )
        AnimatedFadingVisibilityWithDelay(
            visible = registerState == AuthenticationSection.LOG_IN,
            enterDelay = userNameFieldEnterDelay,
            exitDelay = userNameFieldExitDelay
        ) {
            AuthenticationTextField(
                hint = stringResource(id = R.string.authentication_screen_username),
                text = username
            ) { username ->
                onUsernameTextChanged(username)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedFadingVisibilityWithDelay(
            visible = registerState == AuthenticationSection.LOG_IN,
            enterDelay = passwordFieldEnterDelay,
            exitDelay = passwordFieldExitDelay
        ) {
            AuthenticationTextField(
                hint = stringResource(id = R.string.authentication_screen_password),
                text = password
            ) { password ->
                onPasswordTextChanged(password)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        AnimatedFadingVisibilityWithDelay(
            visible = registerState == AuthenticationSection.LOG_IN,
            enterDelay = logInButtonEnterDelay,
            exitDelay = logInButtonExitDelay
        ) {
            AuthenticationButton(text = stringResource(id = R.string.authentication_screen_log_in)) {
                onLoginButtonCLicked()
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        AnimatedFadingVisibilityWithDelay(
            visible = registerState == AuthenticationSection.LOG_IN,
            enterDelay = forgotPasswordTextEnterDelay,
            exitDelay = forgotPasswordTextExitDelay
        ) {
            ForgotPasswordText {
                onForgotPasswordClicked()
            }
        }
    }
}

@Composable
fun ForgotPasswordText(onForgotPasswordClicked: () -> Unit) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onForgotPasswordClicked()
            },
        text = stringResource(id = R.string.authentication_screen_forgot_password),
        style = TextStyle(
            color = Orange,
            fontWeight = FontWeight.Bold
        ),
        fontSize = 16.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
@Preview
fun LogInSectionPreview() {
    MemorikTheme {
        RegisterViewLogin(
            onUsernameTextChanged = {},
            onPasswordTextChanged = {},
            onStateChanged = {},
            onForgotPasswordClicked = {},
            onLoginButtonCLicked = {}
        )
    }
}

private const val userNameFieldExitDelay = 400
private const val passwordFieldExitDelay = 300
private const val logInButtonExitDelay = 200
private const val forgotPasswordTextExitDelay = 100

private const val userNameFieldEnterDelay = 200
private const val passwordFieldEnterDelay = 400
private const val logInButtonEnterDelay = 600
private const val forgotPasswordTextEnterDelay = 700
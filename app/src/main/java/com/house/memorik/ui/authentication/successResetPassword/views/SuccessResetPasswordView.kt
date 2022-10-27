package com.house.memorik.ui.authentication.successResetPassword.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house.memorik.R
import com.house.memorik.ui.authentication.successResetPassword.SuccessResetPasswordViewModel
import com.house.memorik.ui.authentication.successResetPassword.models.SuccessResetPasswordEvent
import com.house.memorik.ui.theme.Gray
import com.house.memorik.ui.theme.MemorikTheme
import com.house.memorik.ui.theme.Orange
import com.house.memorik.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SuccessResetPasswordView(
    viewModel: SuccessResetPasswordViewModel,
    goBackToAuthenticationScreen: () -> Unit,
    goBackToResetPasswordScreen: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailBox()
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.success_reset_password_check_your_email),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = stringResource(id = R.string.success_reset_password_instructions),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        OpenEmailButton { }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.success_reset_password_skip),
            modifier = Modifier.clickable {
                viewModel.obtainEvent(SuccessResetPasswordEvent.OnSkipClicked)
            },
            style = MaterialTheme.typography.h2
        )
    }
    TryAnotherEmailText {
        viewModel.obtainEvent(SuccessResetPasswordEvent.OnTryAnotherEmailAddressClicked)
    }
    HandleSingleEvents(
        scope = scope,
        viewModel = viewModel,
        goBackToAuthenticationScreen = { goBackToAuthenticationScreen() },
        goBackToResetPasswordScreen = { goBackToResetPasswordScreen() }
    )
}

@Composable
fun TryAnotherEmailText(modifier: Modifier = Modifier,onTryAnotherEmailClicked: () -> Unit) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                color = Gray,
                fontSize = 16.sp
            )
        ) {
            append(stringResource(id = R.string.success_reset_password_check_spam_filter))
        }
        append(" ")
        pushStringAnnotation(tag = "try_another", annotation = "")
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily.Default,
                color = Orange,
                fontSize = 17.sp
            )
        ) {
            append(stringResource(id = R.string.success_reset_password_try_another_email_address))
        }
        pop()
    }
    Box(
        modifier = modifier.fillMaxSize().padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString
                    .getStringAnnotations(tag = "try_another", start = offset, end = offset)
                    .firstOrNull()
                    ?.let {
                        onTryAnotherEmailClicked()
                    }
            },
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun OpenEmailButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(210.dp)
            .clip(RoundedCornerShape(10.dp)),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange
        ),
    ) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = stringResource(id = R.string.success_reset_password_open_email),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = White,
                fontWeight = FontWeight.Bold
            ),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun EmailBox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Orange.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            modifier = Modifier.padding(20.dp),
            painter = painterResource(id = R.drawable.image_send_email),
            contentDescription = null
        )
    }
}

@Composable
fun HandleSingleEvents(
    scope: CoroutineScope,
    viewModel: SuccessResetPasswordViewModel,
    goBackToAuthenticationScreen: () -> Unit,
    goBackToResetPasswordScreen: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        scope.launch {
            viewModel.navigateBackToForgotPasswordScreen.collect {
                goBackToResetPasswordScreen()
            }
        }
        scope.launch {
            viewModel.navigateBackToAuthenticationScreen.collect {
                goBackToAuthenticationScreen()
            }
        }
    }
}


@Preview
@Composable
fun SuccessResetPasswordViewPreview() {
    MemorikTheme {
        SuccessResetPasswordView(
            viewModel = SuccessResetPasswordViewModel(),
            goBackToAuthenticationScreen = {},
            goBackToResetPasswordScreen = {}
        )
    }
}
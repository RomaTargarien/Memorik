package com.house.memorik.ui.authentication.forgotPassword.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house.memorik.R
import com.house.memorik.ui.authentication.register.views.AuthenticationTextField
import com.house.memorik.ui.authentication.forgotPassword.ForgotPasswordViewModel
import com.house.memorik.ui.authentication.forgotPassword.models.ForgotPasswordEvent
import com.house.memorik.ui.authentication.forgotPassword.models.ForgotPasswordViewState
import com.house.memorik.ui.theme.Orange
import com.house.memorik.ui.theme.White

@Composable
fun ForgotPasswordView(
    forgotPasswordViewModel: ForgotPasswordViewModel,
    uiState: ForgotPasswordViewState.Display
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp)
    ) {
        Text(
            text = stringResource(R.string.forgot_password_screen_forgot_password),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.forgot_password_screen_instructions),
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = stringResource(R.string.forgot_password_screen_email_address),
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(2.dp))
        AuthenticationTextField(
            hint = stringResource(R.string.forgot_password_screen_email),
            text = uiState.email,
            onTextChanged = {
                forgotPasswordViewModel.obtainEvent(ForgotPasswordEvent.FillEmailTextField(it))
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        ResetPasswordButton(
            text = stringResource(R.string.forgot_password_screen_reset),
            enabled = uiState.isResetButtonEnabled,
            isLoading = uiState.isResetButtonLoading
        ) {
            forgotPasswordViewModel.obtainEvent(ForgotPasswordEvent.OnResetPasswordClicked)
        }
    }
}

@Composable
fun ResetPasswordButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (enabled) Orange else Orange.copy(alpha = 0.7f)
        ),
        elevation = null
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = Color.White
            )
        } else {
            Text(
                text = text,
                style = TextStyle(
                    color = White,
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 24.sp,
            )
        }
    }
}
package com.house.memorik.ui.authentication.register.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.house.memorik.ui.authentication.register.models.EMPTY_STRING
import com.house.memorik.ui.theme.LightGray
import com.house.memorik.ui.theme.Orange
import com.house.memorik.ui.theme.White

@Composable
fun AuthenticationTittleText(
    modifier: Modifier = Modifier,
    text: String,
    scale: Float,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = modifier
            .scale(scale)
            .clickable {
                onClick()
            },
        style = MaterialTheme.typography.h1,
        fontSize = 42.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun OutlinedButton(
    modifier: Modifier = Modifier,
    text: String = EMPTY_STRING,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(60.dp),
        onClick = {
           onClick()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        border = BorderStroke(width = 1.dp, color = Color.Black),
        elevation = null
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            fontSize = 20.sp,
        )
    }
}

@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        onClick = {
            onClick()
        },
        enabled = enabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Orange
        ),
        elevation = null
    ) {
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

@Composable
fun AuthenticationTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    backGroundColor: Color = LightGray,
    onTextChanged: (String) -> Unit
) {
    TextField(
        value = text,
        modifier = modifier
            .fillMaxWidth()
            .height(65.dp),
        onValueChange = {
            onTextChanged(it)
        },
        shape = RoundedCornerShape(10.dp),
        placeholder = {
            AuthenticationTextFieldHint(hint = hint)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = backGroundColor,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
}

@Composable
fun AuthenticationTextFieldHint(
    hint: String = "",
) {
    Text(
        text = hint,
        style = TextStyle(
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        ),
        letterSpacing = 1.5.sp,
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
}
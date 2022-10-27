package com.house.memorik

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.house.memorik.ui.theme.MemorikTheme
import kotlin.math.abs

@Preview
@Composable
fun BottomPreview() {
    MemorikTheme {
        BottomEllipseRow(
            bottomSheetItems = mockBottomList
        ) {

        }
    }
}

val gray = Color(red = 211, green = 211, blue = 211, alpha = 255)
val black = Color.White

data class BottomSheetItem(
    val icon: ImageVector,
    val title: String,
    var ellipseOffsetY: Float = 0f,
    var offsetX: Dp = 0.dp,
    var itemOffsetY: Dp = 0.dp,
    var itemScale: Float = 0f,
    var selectedItemColor: Color = black,
    var unSelectedItemColor: Color = gray,
    var itemColor: Color = Color.White
)

val mockBottomList = listOf(
    BottomSheetItem(
        icon = Icons.Default.Check,
        title = "Check"
    ),
    BottomSheetItem(
        icon = Icons.Default.AccountCircle,
        title = "Person"
    ),
    BottomSheetItem(
        icon = Icons.Default.Add,
        title = "Add"
    ),
    BottomSheetItem(
        icon = Icons.Default.Home,
        title = "Home"
    ),
    BottomSheetItem(
        icon = Icons.Default.Search,
        title = "Search"
    )
)

@Composable
fun BottomEllipseRow(
    modifier: Modifier = Modifier,
    bottomSheetItems: List<BottomSheetItem>,
    selectedItem: BottomSheetItem = bottomSheetItems[0],
    onItemSelected: (BottomSheetItem) -> Unit
) {
    for (item in bottomSheetItems) {
        val ellipseOffsetY: Float by animateFloatAsState(
            targetValue = if (item.title == selectedItem.title) selectedEllipseOffsetY else unselectedEllipseOffsetY,
            animationSpec = tween(durationMillis = timeAnim, easing = LinearOutSlowInEasing)
        )
        val itemOffsetY: Dp by animateDpAsState(
            targetValue = if (item.title == selectedItem.title) selectedItemOffsetY else unselectedItemOffsetY,
            animationSpec = tween(durationMillis = timeAnim, easing = LinearOutSlowInEasing)
        )
        val itemScale: Float by animateFloatAsState(
            targetValue = if (item.title == selectedItem.title) scaledItemPercentage else unScaledItemPercentage,
            animationSpec = tween(durationMillis = timeAnim, easing = LinearOutSlowInEasing)
        )
        val itemIconColor: Color by animateColorAsState(
            targetValue = if (item.title == selectedItem.title) item.selectedItemColor else item.unSelectedItemColor,
            animationSpec = tween(durationMillis = timeAnim, easing = LinearOutSlowInEasing)
        )
        item.itemScale = itemScale
        item.itemOffsetY = itemOffsetY
        item.ellipseOffsetY = ellipseOffsetY
        item.itemColor = itemIconColor
    }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(bottomSheetHeight)
    ) {
        val width = size.width
        val height = size.height
        val startOffset = Offset(width * 0.0f, height * unselectedEllipseOffsetY)
        val path = Path().apply {
            moveTo(startOffset.x, startOffset.y)
            val step = 0.1f / bottomSheetItems.size
            var percentageX = step
            for (item in bottomSheetItems) {
                val mediumColoredPoint2 = Offset(width * percentageX, height * unselectedEllipseOffsetY)
                percentageX += 4 * step
                val mediumColoredPoint3 = Offset(width * percentageX, height * item.ellipseOffsetY)
                percentageX += 4 * step
                val mediumColoredPoint4 = Offset(width * percentageX, height * unselectedEllipseOffsetY)
                percentageX += 2 * step
                val mediumColoredPoint5 = Offset(width * percentageX, height * unselectedEllipseOffsetY)
                standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
                standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
                standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            }
            lineTo(width * 1f, height * 1f)
            lineTo(width * 0f, height * 1f)
            close()
        }
        drawPath(
            path = path,
            brush = Brush.verticalGradient(listOf(Color(0xFF2078EE), Color(0xFF74E6FE)))
        )
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(bottomSheetHeight)
    ) {
        val config = LocalConfiguration.current
        val width = config.screenWidthDp.dp
        val step = 0.1f / bottomSheetItems.size
        var offsetX = step
        for (item in bottomSheetItems) {
            offsetX += 4 * step
            item.offsetX = width.times(offsetX) - bottomSheetIconSize / 2
            offsetX += 6 * step
        }
        for (item in bottomSheetItems) {
            Icon(
                modifier = Modifier
                    .absoluteOffset(item.offsetX, item.itemOffsetY)
                    .scale(item.itemScale)
                    .size(bottomSheetIconSize)
                    .clickable {
                        onItemSelected(item)
                    },
                imageVector = item.icon,
                contentDescription = item.title,
                tint = item.itemColor
            )
        }
    }
}

private const val scaledItemPercentage = 1.5f
private const val unScaledItemPercentage = 1.0f
private const val selectedEllipseOffsetY = 0.1f
private const val unselectedEllipseOffsetY = 0.3f
private val selectedItemOffsetY = 25.dp
private val unselectedItemOffsetY = 35.dp
private val bottomSheetIconSize = 30.dp
private val bottomSheetHeight = 80.dp
private const val timeAnim = 300

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.x + to.x) / 2f,
        abs(from.y + to.y) / 2f
    )
}
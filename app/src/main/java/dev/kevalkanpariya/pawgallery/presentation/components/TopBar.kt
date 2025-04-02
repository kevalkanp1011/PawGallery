package dev.kevalkanpariya.pawgallery.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kevalkanpariya.pawgallery.navigation.Screen

@Composable
fun TopBar(
    title: String = "Generate Dogs!",
    fontSize: TextUnit = 16.sp,
    isRecentGenDogScreen: Boolean = false,
    isSettingsEnabled: Boolean = false,
    onBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = Color.Gray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {

            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "back icon",
                        tint = Color(66, 134, 244)
                    )
                    Text(
                        text = "Back",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color(66, 134, 244)
                        )
                    )
                }
            }


            Text(
                modifier = if (isRecentGenDogScreen) Modifier.align(Alignment.CenterEnd).padding(end = 10.dp) else Modifier.align(Alignment.Center),
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = fontSize
                )
            )

            if (isSettingsEnabled) {
                Button(
                    onClick = {onNavigate(Screen.Settings.route)},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "settings icon",
                        tint = Color(66, 134, 244)
                    )
                }
            }
        }
    }
}
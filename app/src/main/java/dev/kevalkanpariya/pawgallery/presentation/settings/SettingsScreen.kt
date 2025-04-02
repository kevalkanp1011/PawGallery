package dev.kevalkanpariya.pawgallery.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kevalkanpariya.pawgallery.presentation.components.TopBar

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val settings = viewModel.settings.collectAsStateWithLifecycle().value

    Scaffold(
        topBar = {
            TopBar(
                title = "Settings!",
                onBack = onNavigateBack,
                isSettingsEnabled = false,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues).padding(20.dp)) {
            Slider(
                value = settings.cacheSize.toFloat(),
                onValueChange = { viewModel.updateCacheSize(it.toInt()) },
                valueRange = 10f..50f,
                steps = 40
            )
            Text("Cache Size: ${settings.cacheSize}")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Image Quality:")
                RadioButton(
                    selected = settings.imageQuality == "hd",
                    onClick = { viewModel.updateImageQuality("hd") }
                )
                Text("HD")
                RadioButton(
                    selected = settings.imageQuality == "sd",
                    onClick = { viewModel.updateImageQuality("sd") }
                )
                Text("SD")
            }
        }
    }


}
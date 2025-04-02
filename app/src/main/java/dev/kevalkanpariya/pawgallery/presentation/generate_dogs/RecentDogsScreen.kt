package dev.kevalkanpariya.pawgallery.presentation.generate_dogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import dev.kevalkanpariya.pawgallery.presentation.components.TopBar
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states.DogState
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states.DogUiEvent

@Composable
fun RecentDogsScreen(
    state: DogState,
    onEvent: (DogUiEvent) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "My Recently Generated Dogs!",
                onBack = onNavigateBack,
                isSettingsEnabled = false,
                onNavigate = onNavigate,
                fontSize = 14.sp,
                isRecentGenDogScreen = true
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(state.recentDogs) { dog ->
                    AsyncImage(
                        model = dog.imageUrl,
                        contentDescription = "Recent Dog",
                        modifier = Modifier
                            .padding(8.dp)
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Button(
                onClick = { onEvent(DogUiEvent.ClearDogsClicked) },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(66, 134, 244)
                ),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Text("Clear Dogs!")
            }
        }
    }

}

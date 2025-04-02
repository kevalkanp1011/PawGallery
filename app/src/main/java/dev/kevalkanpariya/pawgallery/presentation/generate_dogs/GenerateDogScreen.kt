package dev.kevalkanpariya.pawgallery.presentation.generate_dogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.kevalkanpariya.pawgallery.domain.models.Dog
import dev.kevalkanpariya.pawgallery.presentation.components.TopBar
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states.DogState
import dev.kevalkanpariya.pawgallery.presentation.generate_dogs.states.DogUiEvent

@Composable
fun GenerateDogScreen(
    state: DogState,
    onEvent: (DogUiEvent) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Generate Dogs!",
                onBack = onNavigateBack,
                isSettingsEnabled = true,
                onNavigate = onNavigate
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    state.isLoading -> CircularProgressIndicator()
                    state.currentDog != null -> DogImage(state.currentDog)
                    state.errorMessage != null -> ErrorMessage(state.errorMessage)
                }



            }

            Box(Modifier.fillMaxSize()) {
                Button(
                    onClick = { onEvent(DogUiEvent.GenerateDogClicked) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(66, 134, 244)
                    ),
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 100.dp),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text("Generate!")
                }
            }
        }
    }
}



@Composable
private fun DogImage(dog: Dog) {
    AsyncImage(
        model = dog.imageUrl,
        contentDescription = "Random Dog",
        modifier = Modifier
            .size(300.dp)
            .padding(30.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun ErrorMessage(message: String) {
    Text(
        text = message,
        color = Color.Red,
        modifier = Modifier.padding(16.dp)
    )
}
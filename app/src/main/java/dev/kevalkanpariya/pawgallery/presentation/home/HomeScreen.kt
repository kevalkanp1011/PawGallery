package dev.kevalkanpariya.pawgallery.presentation.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kevalkanpariya.pawgallery.navigation.Screen

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Random Dog Generator",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { onNavigate(Screen.GenerateDogs.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(66, 134, 244)
            ),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(text = "Generate Dogs!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavigate(Screen.RecentDogs.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(66, 134, 244)
            ),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(text = "My Recently Generated Dogs!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        MadeWithLove()
    }
}



@Composable
fun MadeWithLove() {
    val context = LocalContext.current
    val githubUrl = "https://github.com/kevalkanp1011"

    val annotatedString = buildAnnotatedString {
        append("Made with ❤ by ")
        pushStringAnnotation(tag = "GITHUB", annotation = githubUrl)
        withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold)) {
            append("Keval Kanpariya")
        }
        pop()
    }

    Text(
        text = annotatedString,
        fontSize = 14.sp,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
            context.startActivity(intent)
        }
    )
}


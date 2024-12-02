package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    // List of artworks with image and description
    val artworks = listOf(
        Artwork(R.drawable.doggo, "Doggo", "Android (2021)"),
        Artwork(R.drawable.cat, "Cat", "Compose (2022)"),
        Artwork(R.drawable.elk, "Elk", "Kotlin (2023)"),
        Artwork(R.drawable.fox, "Fox", "Google (2024)"),
        Artwork(R.drawable.squirrel, "Squirrel", "Alphabet (2025)")
    )

    // Current index state
    var currentIndex by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).align(Alignment.Center)
        ) {
            // Display current artwork
            DisplayArt(artworks[currentIndex].image)
            DisplayText(artworks[currentIndex].title, artworks[currentIndex].photographer)
            DisplayButtons(
                onPrevious = {
                    currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
                },
                onNext = {
                    currentIndex = (currentIndex + 1) % artworks.size
                }
            )
        }
    }
}

@Composable
internal fun DisplayArt(imageRes: Int, modifier: Modifier = Modifier) {
    // Background surface
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color(0xFFFFFFFF),
        shadowElevation = 16.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "doggo",
            modifier = modifier
                .padding(32.dp)
        )
    }
}

@Composable
internal fun DisplayText(title: String, photographer: String, modifier: Modifier = Modifier)
{
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = Color(0xFFCCCCCC),
        shape = RectangleShape
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(16.dp)
        ){
            Text(
                text = title,
                fontSize = 50.sp,
                fontWeight = FontWeight.Light,
                modifier = modifier.padding(bottom = 16.dp)
            )
            Text(
                text = photographer,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
            )
        }
    }
}

@Composable
internal fun DisplayButtons(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(36.dp) // Space between buttons
        ) {
            Button(onClick = onPrevious,
                modifier = Modifier
                    .width(150.dp)) {
                Text("Previous")
            }
            Button(onClick = onNext,
                modifier = Modifier
                    .width(150.dp)) {
                Text("Next")
            }
        }
    }
}

data class Artwork(val image: Int, val title: String, val photographer: String)
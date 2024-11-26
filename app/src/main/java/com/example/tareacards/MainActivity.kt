package com.example.tareacards

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tareacards.ui.theme.TareaCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareaCardsTheme {
                ImageStyleCards()
            }
        }
    }
}

@Composable
fun ImageStyleCards() {
    val cardsData = listOf(
        CardData(
            "https://static.wikia.nocookie.net/ytp/images/f/f1/Fernanfloo.jpg/revision/latest?cb=20231226205038&path-prefix=es",
            "fernanfloo",
            "Holaaa chicos",
            "https://static.wikia.nocookie.net/ficcion-sin-limites/images/b/bf/Fernanfloo.png/revision/latest?cb=20210313142223&path-prefix=es"
        ),
        CardData(
            "https://i.pinimg.com/736x/28/51/96/28519697ee1da70cc385bc8ea1bde32c.jpg",
            "fernantubehn",
            "",
            "https://i.pinimg.com/736x/62/6f/a6/626fa601234cae32fc9133716ae7320a.jpg"        ),
        CardData(
            "https://prod-media.beinsports.com/image/1729203628634_ee83c009-2cdd-4d56-afdc-2f15f30d31e5.jpg?ver=28-06-2024",
            "messi",
            "\uD83D\uDE0E\uD83D\uDD25",
            "https://fifpro.org/media/fhmfhvkx/messi-world-cup.jpg"
        ),
        CardData(
            "https://pbs.twimg.com/media/Gb-k5htXQAEOVkt?format=jpg",
            "movistarkoi",
            "Sólo quedan 2 semanas para volver a veros a todos \uD83D\uDD1C",
            "https://pbs.twimg.com/profile_images/1742973202354118656/LhkNQMKO_400x400.jpg"
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        items(cardsData) { card ->
            Cards(
                imageUrl = card.imageUrl,
                username = card.username,
                description = card.description,
                avatarUrl = card.avatarUrl
            )
        }
    }
}

data class CardData(
    val imageUrl: String,
    val username: String,
    val description: String,
    val avatarUrl: String
)

@Composable
fun Cards(imageUrl: String, username: String, description: String, avatarUrl: String) {
    val isLiked = remember { mutableStateOf(false) }


    Log.d("ImageUrl", "Cargando imagen de: $imageUrl")
    Log.d("AvatarUrl", "Cargando avatar de: $avatarUrl")

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }


            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {

                                isLiked.value = !isLiked.value
                            }
                        )
                    },
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isLiked.value) "❤️" else "🤍",
                        fontSize = 24.sp,
                        color = if (isLiked.value) Color.Red else Color.Black,
                        modifier = Modifier.clickable { isLiked.value = !isLiked.value }
                    )
                }
                Text(
                    text = username,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

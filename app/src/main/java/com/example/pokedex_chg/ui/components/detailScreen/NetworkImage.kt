package com.example.pokedex_chg.ui.components.detailScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@SuppressLint("RememberReturnType")
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val painter: AsyncImagePainter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = url)
            .apply { crossfade(true) }
            .build()
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clip(RoundedCornerShape(0.dp, 0.dp, 35.dp, 35.dp))
    )
}

package net.rishiz.acharyaprashant.view

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import net.rishiz.acharyaprashant.R
import net.rishiz.acharyaprashant.navigation.Screen

/**
 * SplashSceen UI
 */
@Composable
fun SplashScreen(navController: NavController) {
//Background Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
    ) {
        Image(
            painter = painterResource(R.drawable.gita_bg),
            contentDescription = "backgroung image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
        )
    }
//Logo Layout
    Box(
        modifier = Modifier.offset(x = 250.dp, y = 50.dp)
    ) {
        Card(
            shape = CircleShape, elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)

            )
        }
    }

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
//        Animating splash screen
//        scale.animateTo(targetValue = 0.3f, animationSpec = tween(durationMillis = 500, easing = {
//            OvershootInterpolator(2f).getInterpolation(it)
//        }))
        delay(2000L)
        navController.navigate(Screen.MainScreen.route) {
            popUpTo(Screen.SplashScreen.route) { inclusive = true }
        }
    }
}

package org.schoolmanager.project

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import schoolmanager.composeapp.generated.resources.Res
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color

import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

import schoolmanager.composeapp.generated.resources.CopyRigthECAM

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    var startAnimation by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(targetValue = if (startAnimation) 0f else 1f)

    LaunchedEffect(Unit) {
        delay(2000) // Durée de l'animation
        startAnimation = true
        delay(1000) // Temps supplémentaire pour laisser l'animation finir avant de naviguer
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.CopyRigthECAM),
            contentDescription = "Logo",
            modifier = Modifier
                .alpha(alpha)
                .size(300.dp)
        )
    }
}

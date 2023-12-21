package com.dicoding.mozart.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mozart.R
import com.dicoding.mozart.navigation.Screen
import com.dicoding.mozart.ui.components.GradientButton
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    LaunchedEffect(true) {
        // Delay for 2 seconds
        delay(2000)
        // Redirect to Screen.Home
        navHostController.navigate(route = Screen.Home.route)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(
                id = R.drawable.shapeyellow
            ),
            contentDescription = "",
            alignment = Alignment.TopStart
        )
        Image(
            modifier = modifier
                .fillMaxWidth()
                .size(300.dp),
            painter = painterResource(
                id = R.drawable.splashimage
            ),
            contentDescription = "",
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Get think with MozArt",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = "With us, discover a different experience of visiting museums in our app, where it's not about seeing and leaving, but seeing and engaging.",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 13.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        val cornerRadius = 16.dp
        val gradientColor = listOf(Color(0xFFFFF95B), Color(0xFFFF930F))
//        GradientButton(
//            gradientColors = gradientColor,
//            cornerRadius = cornerRadius,
//            nameButton = "Get Started",
//            roundedCornerShape = RoundedCornerShape(topStart = 30.dp),
//            onClick = {
//                navHostController.navigate(route = Screen.Home.route)
//            }
//        )
    }
}


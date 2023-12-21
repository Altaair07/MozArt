package com.dicoding.mozart.ui.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.mozart.ui.theme.MozArtTheme

@Composable
fun Title(){
    Column{

    Text(text = "Explore")
    Text(text = "Mozart", fontWeight = FontWeight.Bold, fontSize = 32.sp)
    }

}


@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    MozArtTheme {
        Title()
    }
}
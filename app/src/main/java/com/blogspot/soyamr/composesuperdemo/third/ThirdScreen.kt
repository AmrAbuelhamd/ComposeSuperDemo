package com.blogspot.soyamr.composesuperdemo.third

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.blogspot.soyamr.composesuperdemo.ScaffoldScreen

@Composable
fun ThirdBody(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    Button(onClick = { onClick(ScaffoldScreen.First.name) }) {
        Text(text = "Third")
    }
}

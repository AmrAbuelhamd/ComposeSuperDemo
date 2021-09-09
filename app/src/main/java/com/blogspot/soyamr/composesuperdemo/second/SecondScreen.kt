package com.blogspot.soyamr.composesuperdemo.second

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.blogspot.soyamr.composesuperdemo.ScaffoldScreen

@Composable
fun SecondBody(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    Button(onClick = { onClick(ScaffoldScreen.Third.name) }) {
        Text(text = "Second")
    }
}

package com.example.unoclase.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyComplexLayout(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Red)
        )
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
        )
        {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.Black)
                )
                Column(
                    Modifier
                        .weight(2f)
                        .fillMaxHeight()
                ) {
                    Box(
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(Color.Green),
                        contentAlignment = Alignment.Center
                    )
                    { Text("Verde") }

                    Box(
                        Modifier
                            .weight(0.1f)
                            .fillMaxWidth()
                            .background(Color.Gray)
                    )
                }

            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .width(300.dp)
                .background(Color.Yellow)
        )

    }
}

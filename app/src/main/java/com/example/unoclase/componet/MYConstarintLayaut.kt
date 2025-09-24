package com.example.unoclase.componet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


// Añadir a libs.versions.toml y builsd.gradle
@Composable
fun MyBasicConstaintLayout(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (box1, box2, box3, box4, box5) = createRefs()  // son muchas, ponemos s al final
        //val boxx=createRef()
        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Red)
            .constrainAs(box1) {})
        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Yellow)
            .constrainAs(box2) {})
        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Gray)
            .constrainAs(box3) {})

        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Blue)
            .constrainAs(box5) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                top.linkTo(parent.top)  // se iria a la mitad
                start.linkTo(parent.start)  // se iria al centro
            })

        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Green)
            .constrainAs(box4) {
                bottom.linkTo(box5.top, margin = 30.dp)
            })
    }
}
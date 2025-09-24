package com.example.unoclase.componet


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout


// Añadir a libs.versions.toml y builsd.gradle

@Composable
fun MyAdConstaintLayoutGuide(modifier: Modifier = Modifier) {
    // Un ConstraintLayout que ocupa todo el tamaño disponible
    ConstraintLayout(modifier = modifier.fillMaxSize()) {

        // Creamos una referencia para el Box rojo
        val BoxRed = createRef()

        // Creamos una guía (guideline) desde la parte superior al 10% de la altura del padre.
        // No es un componente visible: sirve como “línea virtual” para anclar vistas.
        val topGuide = createGuidelineFromTop(0.1f)  // 10%

        Box(
            modifier = Modifier
                .size(125.dp)              // Tamaño fijo 125dp
                .background(Color.Red)     // Fondo rojo
                .constrainAs(BoxRed) {
                    // Anclamos el borde superior del Box a la guía.
                    // El Box quedará alineado con esa línea al 10% del alto del padre.
                    top.linkTo(topGuide)
                }
        )
    }
}


@Composable
fun MyConstraintBarrier(modifier: Modifier = Modifier) {
    // ConstraintLayout a pantalla completa
    ConstraintLayout(modifier = modifier.fillMaxSize()) {

        // Tres refs para los Box
        val (boxRed, boxBlue, boxGreen) = createRefs()

        // Creamos una barrera al "end" (derecha) basada en boxRed y boxBlue.
        // La barrera es una línea virtual que se posiciona en el límite más externo de los elementos dados.
        // En este caso, quedará donde llegue más a la derecha boxRed o boxBlue.
        val barrier = createEndBarrier(boxRed, boxBlue)

        // Box rojo: arriba-izquierda
        Box(
            modifier = Modifier
                .size(65.dp)
                .background(Color.Red)
                .constrainAs(boxRed) {
                    top.linkTo(parent.top)       // pegado al top del padre
                    start.linkTo(parent.start)   // pegado al start (izquierda) del padre
                }
        )

        // Box azul: debajo del rojo, alineado al inicio del padre
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Blue)
                .constrainAs(boxBlue) {
                    start.linkTo(parent.start)   // alineado al inicio del padre
                    top.linkTo(boxRed.bottom)    // debajo del rojo
                }
        )

        // Box verde: su borde start se ancla a la barrera.
        // Resultado: el verde quedará situado a la derecha de cualquiera que llegue más lejos (rojo o azul).
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(Color.Green)
                .constrainAs(boxGreen) {
                    start.linkTo(barrier)        // empieza justo donde termina la barrera
                }
        )
    }
}

@Composable
fun ConstraintChain(modifier: Modifier = Modifier) {
    // Layout con cadenas (chains) verticales empaquetadas
    ConstraintLayout(modifier = modifier.fillMaxSize()) {

        // Referencias para los tres Box
        val (boxRed, boxBlue, boxGreen) = createRefs()

        // Box rojo: centrado horizontalmente, limita arriba con el padre y abajo con el azul
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Red)
                .constrainAs(boxRed) {
                    start.linkTo(parent.start)   // para centrar, linkeamos start y end al padre...
                    end.linkTo(parent.end)       // ...y el sistema lo centra automáticamente
                    top.linkTo(parent.top)       // parte superior ligada al top del padre
                    bottom.linkTo(boxBlue.top)   // parte inferior ligada al top del azul
                }
        )

        // Box azul: centrado horizontalmente, entre rojo y verde
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Blue)
                .constrainAs(boxBlue) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(boxRed.bottom)    // debajo del rojo
                    bottom.linkTo(boxGreen.top)  // encima del verde
                }
        )

        // Box verde: centrado horizontalmente, limita abajo con el padre y arriba con el azul
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.Green)
                .constrainAs(boxGreen) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(boxBlue.bottom)   // debajo del azul
                    bottom.linkTo(parent.bottom) // encima del borde inferior del padre
                }
        )

        // Creamos una cadena vertical con los tres Boxes.
        // ChainStyle.Packed agrupa los elementos juntos en el centro del espacio disponible.
        // (Otros estilos: Spread, SpreadInside)
        createVerticalChain(
            boxRed,
            boxBlue,
            boxGreen,
            chainStyle = ChainStyle.Packed
        )
    }
}








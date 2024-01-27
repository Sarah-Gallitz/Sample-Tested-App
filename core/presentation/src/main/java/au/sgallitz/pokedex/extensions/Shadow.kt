package au.sgallitz.pokedex.extensions

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

fun Modifier.shadowOnScroll(lazyGridState: LazyGridState): Modifier = composed {
    val elevation = animateDpAsState(
        targetValue = if (lazyGridState.isAtTop()) {
            0.dp
        } else {
            4.dp
        },
        label = "topAppBarElevationAnimation"
    )

    this.graphicsLayer {
        this.shadowElevation = elevation.value.toPx()
    }
}

fun Modifier.shadowOnScroll(lazyListState: LazyListState): Modifier = composed {
    val elevation = animateDpAsState(
        targetValue = if (lazyListState.isAtTop()) {
            0.dp
        } else {
            4.dp
        },
        label = "topAppBarElevationAnimation"
    )

    this.graphicsLayer {
        this.shadowElevation = elevation.value.toPx()
    }
}

fun Modifier.shadowOnScroll(scrollState: ScrollState): Modifier = composed {
    val elevation = animateDpAsState(
        targetValue = if (scrollState.isAtTop()) {
            0.dp
        } else {
            4.dp
        },
        label = "topAppBarElevationAnimation"
    )

    this.graphicsLayer {
        this.shadowElevation = elevation.value.toPx()
    }
}

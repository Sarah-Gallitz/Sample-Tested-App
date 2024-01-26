package au.sgallitz.pokedex.extensions

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun LazyGridState.isAtBottom(): Boolean {
    return remember(this) {
        derivedStateOf {
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                val viewportSize = layoutInfo.viewportSize

                (
                    lastVisibleItem.index + 1 == layoutInfo.totalItemsCount &&
                        lastVisibleItem.offset.y + lastVisibleItem.size.height <= viewportSize.height &&
                        lastVisibleItem.offset.x + lastVisibleItem.size.width <= viewportSize.width
                    )
            }
        }
    }.value
}

@Composable
fun LazyGridState.totalItems(): Int {
    return remember(this) {
        derivedStateOf { layoutInfo.totalItemsCount }
    }.value
}

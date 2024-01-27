package au.sgallitz.pokedex.extensions

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun LazyListState.isAtBottom(): Boolean {
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
                        when (layoutInfo.orientation) {
                            Orientation.Vertical -> lastVisibleItem.offset + lastVisibleItem.size <= viewportSize.height
                            Orientation.Horizontal -> lastVisibleItem.offset + lastVisibleItem.size <= viewportSize.width
                        }
                    )
            }
        }
    }.value
}

@Composable
fun LazyListState.isAtTop(): Boolean {
    return remember(this) {
        derivedStateOf {
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount == 0) {
                true
            } else {
                val firstVisibleItem = visibleItemsInfo.first()
                (firstVisibleItem.index == 0 && firstVisibleItem.offset == 0)
            }
        }
    }.value
}

@Composable
fun LazyListState.totalItems(): Int {
    return remember(this) {
        derivedStateOf { layoutInfo.totalItemsCount }
    }.value
}

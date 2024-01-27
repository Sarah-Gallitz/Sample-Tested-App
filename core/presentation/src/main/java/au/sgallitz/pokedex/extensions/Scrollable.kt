package au.sgallitz.pokedex.extensions

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun ScrollState.isAtBottom(): Boolean {
    return remember(this) {
        derivedStateOf { !canScrollForward }
    }.value
}

@Composable
fun ScrollState.isAtTop(): Boolean {
    return remember(this) {
        derivedStateOf { !canScrollBackward }
    }.value
}

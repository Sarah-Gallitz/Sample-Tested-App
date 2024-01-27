package au.sgallitz.pokedex.extensions

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat

fun Context.getColorFromAttribute(@AttrRes attr: Int): Color {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    val color = ContextCompat.getColor(this, typedValue.resourceId)

    return Color(color)
}

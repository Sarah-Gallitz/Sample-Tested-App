package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import android.content.Context
import android.util.DisplayMetrics

object GridUtils {
    const val gridItemSizeDp = 152

    fun getNumColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (displayMetrics.widthPixels / dpToPx(
            gridItemSizeDp,
            context
        ))
    }

    private fun dpToPx(dp: Int, context: Context): Int {
        val metrics = context.resources.displayMetrics
        return (dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }
}
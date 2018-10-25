package sampletestedapp.sarahgallitz.com.sampletestedapp.presentation

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.Settings
import android.support.v7.content.res.AppCompatResources.getDrawable
import android.util.AttributeSet
import android.widget.ProgressBar
import sampletestedapp.sarahgallitz.com.sampletestedapp.R

// Code taken and modified from https://gist.github.com/jsonfry/374c3eb5b8eb88d2a674100d6bfecd92
class UiTestableProgressBar(context: Context, attrs: AttributeSet) : ProgressBar(context, attrs) {
    override fun setIndeterminateDrawable(drawable: Drawable) {
        val hideIndeterminateDrawable = if (Build.VERSION.SDK_INT >= 17) {
            Settings.Global.getFloat(context.contentResolver, Settings.Global.ANIMATOR_DURATION_SCALE, 1f) == 0f
        } else {
            true
        }

        super.setIndeterminateDrawable(
                if (hideIndeterminateDrawable) getDrawable(context, R.drawable.loading_bar_non_animated) else drawable
        )
    }
}

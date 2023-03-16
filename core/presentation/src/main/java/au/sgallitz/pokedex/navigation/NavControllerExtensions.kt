package au.sgallitz.pokedex.navigation

import android.app.Activity
import androidx.navigation.NavController

fun NavController.closeCurrentActivity() {
    val activity = (this.context as? Activity)
    if (activity != null) {
        activity.finish()
    } else {
        // TODO: Handle error
    }
}

fun NavController.closeCurrentScreen() {
    val didPopBackStack = this.popBackStack()
    if (!didPopBackStack) {
        this.closeCurrentActivity()
    }
}

package sampletestedapp.sarahgallitz.com.sampletestedapp.debug.debug.utils

import android.os.IBinder
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnitRunner
import android.support.test.uiautomator.UiDevice
import java.lang.reflect.Method
import java.util.*

/**
 * Disable animations so that they do not interfere with Espresso tests.
 *
 * Source: https://code.google.com/p/android-test-kit/wiki/DisablingAnimations
 */
class SystemAnimations : AndroidJUnitRunner() {
    private val mSetAnimationScalesMethod: Method
    private val mGetAnimationScalesMethod: Method
    private val mWindowManagerObject: Any

    init {
        UiDevice
            .getInstance(InstrumentationRegistry.getInstrumentation())
            .executeShellCommand(
                "pm grant ${InstrumentationRegistry.getTargetContext().packageName} " +
                    "android.permission.SET_ANIMATION_SCALE"
            )

        try {
            val windowManagerStubClazz = Class.forName("android.view.IWindowManager\$Stub")
            val asInterface = windowManagerStubClazz.getDeclaredMethod("asInterface", IBinder::class.java)

            val serviceManagerClazz = Class.forName("android.os.ServiceManager")
            val getService = serviceManagerClazz.getDeclaredMethod("getService", String::class.java)

            val windowManagerClazz = Class.forName("android.view.IWindowManager")

            mSetAnimationScalesMethod = windowManagerClazz.getDeclaredMethod("setAnimationScales", FloatArray::class.java)
            mGetAnimationScalesMethod = windowManagerClazz.getDeclaredMethod("getAnimationScales")

            val windowManagerBinder = getService.invoke(null, "window") as IBinder
            mWindowManagerObject = asInterface.invoke(null, windowManagerBinder)
        } catch (e: Exception) {
            throw RuntimeException("Failed to access animation methods", e)
        }
    }

    fun disableAll() {
        setAnimationScaleFactors(0.0f)
    }

    fun enableAll() {
        setAnimationScaleFactors(1.0f)
    }

    private fun setAnimationScaleFactors(scaleFactor: Float) {
        val scaleFactors = mGetAnimationScalesMethod.invoke(mWindowManagerObject) as FloatArray
        Arrays.fill(scaleFactors, scaleFactor)
        mSetAnimationScalesMethod.invoke(mWindowManagerObject, scaleFactors)
    }
}

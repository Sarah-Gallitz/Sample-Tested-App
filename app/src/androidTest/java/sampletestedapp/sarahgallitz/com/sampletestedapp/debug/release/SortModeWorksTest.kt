package sampletestedapp.sarahgallitz.com.sampletestedapp.debug.release

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.By
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import android.support.test.uiautomator.Until
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

const val EXTRA_LONG_WAIT_TIMEOUT: Long = 60 * 1000
const val LONG_WAIT_TIMEOUT: Long = 10 * 1000
const val MEDIUM_WAIT_TIMEOUT: Long = 5 * 1000
const val SHORT_WAIT_TIMEOUT: Long = 500

@RunWith(AndroidJUnit4::class)
class SortModeWorksTest {
    val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())!!
    val context: Context
        get() = InstrumentationRegistry.getContext()!!

    lateinit var packageName: String

    private fun launchApp() {
        device.pressHome()

        val launcherPackage = device.launcherPackageName;
        Assert.assertNotNull(launcherPackage)

        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), SHORT_WAIT_TIMEOUT)

        packageName = context.packageName.removeSuffix(".test")

        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        context.startActivity(intent)

        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), SHORT_WAIT_TIMEOUT)
    }

    @Test
    fun test_sort_mode_can_be_changed() {
        launchApp()

        device.findObject(UiSelector().resourceId("${packageName}:id/image_view")).waitForExists(LONG_WAIT_TIMEOUT)
        device.findObject(UiSelector().resourceId("${packageName}:id/image_view")).exists()

        device.findObject(UiSelector().resourceId("${packageName}:id/sort_mode_button")).click()

        device.findObject(UiSelector().text("New")).exists()
        device.findObject(UiSelector().text("Top")).exists()
        device.findObject(UiSelector().text("Hot")).exists()

        device.findObject(UiSelector().text("Hot")).click()

        device.findObject(UiSelector().resourceId("${packageName}:id/image_view")).waitForExists(LONG_WAIT_TIMEOUT)
        device.findObject(UiSelector().resourceId("${packageName}:id/image_view")).exists()
    }
}

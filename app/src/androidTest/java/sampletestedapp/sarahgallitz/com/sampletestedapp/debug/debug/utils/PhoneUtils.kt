import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.support.test.InstrumentationRegistry
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiObjectNotFoundException
import android.support.test.uiautomator.UiSelector
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.jraska.falcon.Falcon
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object PhoneUtils {
    private val TAG = PhoneUtils::class.java.simpleName

    fun takeScreenshot(activity: Activity, fileName: String) {
        allowPermissionsIfNeeded(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, activity)
        allowPermissionsIfNeeded(android.Manifest.permission.READ_EXTERNAL_STORAGE, activity)

        try {
            val bitmap = Falcon.takeScreenshotBitmap(activity)
            activity.runOnUiThread {
                val now = Date()
                val format = SimpleDateFormat("yyyy.MM.dd_hh.mm.ss")

                saveScreenshot(bitmap, fileName + "-" + format.format(now))
            }
        } catch (e: OutOfMemoryError) {
            Log.w(TAG, "Couldn't take screenshot ${e.message}")
        }
    }

    fun getTestBitmap(bitmapName: String): Bitmap {
        val context = InstrumentationRegistry.getContext()
        val id = context.resources.getIdentifier(bitmapName, "drawable", context.packageName)
        return BitmapFactory.decodeResource(context.resources, id)
    }

    private fun saveScreenshot(screenshot: Bitmap, fileName: String) {
        // 'test-screenshots' is the default folder checked by multiple cloud providers for screenshots
        val path = Environment.getExternalStorageDirectory().absolutePath + "/test-screenshots/" + fileName + ".png"

        try {
            val imageFile = File(path)
            if (!imageFile.parentFile.exists() && !imageFile.parentFile.mkdirs()) {
                Log.i(TAG, "Failed to create test-screenshots folder " + imageFile.parentFile)
            }
            imageFile.createNewFile()

            val outputStream = FileOutputStream(imageFile)
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

            Log.i(TAG, "Saved screenshot to " + path)
        } catch (e: Exception) {
            Log.w(TAG, "Failed to save screenshot to $path due to $e")
        }
    }


    private fun allowPermissionsIfNeeded(permissionNeeded: String, activity: Activity) {
        fun hasNeededPermission(permissionNeeded: String): Boolean {
            val context = InstrumentationRegistry.getContext()
            val permissionStatus = ContextCompat.checkSelfPermission(context, permissionNeeded)
            return permissionStatus == PackageManager.PERMISSION_GRANTED
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !hasNeededPermission(permissionNeeded)) {
                ActivityCompat.requestPermissions(activity, arrayOf(permissionNeeded), 0)
                val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                val allowPermissions = device.findObject(UiSelector().clickable(true).checkable(false).index(1))
                if (allowPermissions.exists()) {
                    allowPermissions.click()
                }
            }
        } catch (e: UiObjectNotFoundException) {
            Log.d(TAG, "There is no permissions dialog to interact with")
        }
    }
}
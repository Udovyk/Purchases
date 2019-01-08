package udovyk.homework.purchases.common

import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@Throws(IOException::class)
fun createImageFile(context: Context?): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val storageDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    return File.createTempFile(imageFileName, ".jpg", storageDir)
}

fun isPermissionGranted(fragment: Fragment, permission: String): Boolean =
    (ContextCompat.checkSelfPermission(fragment.context!!, permission) == PackageManager.PERMISSION_GRANTED)
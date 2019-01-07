package udovyk.homework.purchases.common

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


@Throws(IOException::class)
fun createImageFile(context: Context?): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val storageDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    // Save a file: path for use with ACTION_VIEW intents
    return File.createTempFile(imageFileName, ".jpg", storageDir)
}
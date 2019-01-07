package udovyk.homework.purchases.common

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import udovyk.homework.purchases.BuildConfig
import java.io.File
import java.io.IOException


fun Activity?.takePicture(): File? {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    var photoFile: File? = null
    val photoURI: Uri
    try {
        photoFile = createImageFile(this)
    } catch (ex: IOException) {
        ex.printStackTrace()
    }

    if (photoFile != null) {
        photoURI = FileProvider.getUriForFile(this!!, BuildConfig.APPLICATION_ID + ".provider", photoFile)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        if (intent.resolveActivity(this.packageManager) != null) {
            startActivityForResult(intent, Constants.REQUEST_IMAGE_CAPTURE)
        }
    }
    return photoFile
}

fun Activity?.pickPicture() {
    val photoPickerIntent = Intent(Intent.ACTION_PICK)
    photoPickerIntent.type = "image/*"
    this?.startActivityForResult(photoPickerIntent, Constants.REQUEST_IMAGE_PICK)
}
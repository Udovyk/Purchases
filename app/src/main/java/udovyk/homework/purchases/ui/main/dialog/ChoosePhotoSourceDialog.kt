package udovyk.homework.purchases.ui.main.dialog

import android.Manifest
import android.app.Dialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import io.reactivex.disposables.CompositeDisposable
import udovyk.homework.purchases.R

class ChoosePhotoSourceDialog : DialogFragment() {

    companion object {
        private const val READ_REQUEST_CODE = 0
        private const val CAMERA_REQUEST_CODE = 1
        const val TAG: String = "ChoosePhotoSourceDialog"

        fun newInstance(): ChoosePhotoSourceDialog {
            return ChoosePhotoSourceDialog()
        }
    }

    @BindView(R.id.camera_container)
    lateinit var cameraContainer : ConstraintLayout

    @BindView(R.id.gallery_container)
    lateinit var galleryContainer : ConstraintLayout


    var callbacks: Callbacks? = null

    //todo change need_permission text
    private val needPermissionsDialog by lazy {
        android.app.AlertDialog.Builder(activity)
            .setPositiveButton(R.string.ok, { self, _ ->

                //todo delete later ???????????????????
                //callbacks?.onSettingsOpen()
                self.dismiss()
            })
            .setMessage(R.string.need_permission)
            .create()
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val customView = activity!!.layoutInflater.inflate(R.layout.dialog_choose_source, null)
        //todo change { _, _ -> dismiss()} later and above
        ButterKnife.bind(this, customView)
        handleClicks()
        val builder = AlertDialog.Builder(context!!)
            .setTitle(R.string.choose_from)
            .setView(customView)
            .setNegativeButton(android.R.string.cancel, { _, _ -> dismiss()})

        return builder.create()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        grantResults.forEachIndexed { i, result ->
            if (result == PackageManager.PERMISSION_DENIED) {
                if(!shouldShowRequestPermissionRationale(permissions[i])) {
                    dismiss()
                    needPermissionsDialog.show()
                }
                return
            }
        }

        when (requestCode) {
            READ_REQUEST_CODE -> callbacks?.onGalleryClicked()
            CAMERA_REQUEST_CODE -> callbacks?.onCameraClicked()
        }
        dismiss()

    }

    private fun handleClicks() {

        cameraContainer.setOnClickListener {
            if (!isPermissionGranted(this@ChoosePhotoSourceDialog, Manifest.permission.CAMERA)) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
            } else {
                callbacks?.onCameraClicked()
                dismiss()
            }
        }

        galleryContainer.setOnClickListener {
            if (!isPermissionGranted(this@ChoosePhotoSourceDialog, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_REQUEST_CODE)
            } else {
                callbacks?.onGalleryClicked()
                dismiss()
            }
        }

    }

    //todo REFACTOR, tools?
    fun isPermissionGranted(fragment: Fragment, permission: String): Boolean =
        (ContextCompat.checkSelfPermission(fragment.context!!, permission) == PackageManager.PERMISSION_GRANTED)

    interface Callbacks {
        fun onGalleryClicked()
        fun onCameraClicked()
        //fun onSettingsOpen()
    }
}
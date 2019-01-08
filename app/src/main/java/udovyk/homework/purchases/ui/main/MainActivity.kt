package udovyk.homework.purchases.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import udovyk.homework.purchases.R
import udovyk.homework.purchases.common.Constants.REQUEST_IMAGE_CAPTURE
import udovyk.homework.purchases.common.Constants.REQUEST_IMAGE_PICK
import udovyk.homework.purchases.common.pickPicture
import udovyk.homework.purchases.common.takePicture
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BaseActivity
import udovyk.homework.purchases.ui.bought.BoughtFragment
import udovyk.homework.purchases.ui.main.adapter.SectionsPageAdapter
import udovyk.homework.purchases.ui.main.dialog.ChoosePhotoSourceDialog
import udovyk.homework.purchases.ui.purchases.PurchasesFragment
import java.io.File
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    companion object {
        const val TAG = "MainActivity"
    }

    @BindView(R.id.container)
    lateinit var viewPager: ViewPager

    @BindView(R.id.tabs)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    //todo inject later
    var sectionsPageAdapter = SectionsPageAdapter(supportFragmentManager)


    //todo test
    private var photoFile: File? = null
    //todo test

    private val addButtonCallback by lazy {
        object : ChoosePhotoSourceDialog.Callbacks {
            override fun onGalleryClicked() {
                pickPicture()
            }

            override fun onCameraClicked() {
                photoFile = takePicture()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        fab.setOnClickListener {
            Log.d("Test", "fab was clicked")
            val dialog = ChoosePhotoSourceDialog.newInstance()
            dialog.callbacks = addButtonCallback
            dialog.show(supportFragmentManager, ChoosePhotoSourceDialog.TAG)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_PICK -> {
                    val uri = data?.data
                    Log.d("Test", "onActivityResult " + uri.toString() )
                    val purchase = PurchaseEntity(imageUri = uri.toString())
                    addPurchaseToList(purchase)

                }
                REQUEST_IMAGE_CAPTURE -> {
                    val uri = Uri.fromFile(photoFile)
                    Log.d("Test", "onActivityResult " + uri.toString() )
                    val purchase = PurchaseEntity(imageUri = uri.toString())
                    addPurchaseToList(purchase)


                }
            }
        }
    }


    override fun addPurchaseToList(purchaseEntity: PurchaseEntity) {
        presenter.addPurchase(purchaseEntity)
    }




    override fun setUpViewPager(viewPager: ViewPager) {
        sectionsPageAdapter.addFragment(PurchasesFragment.newInstance(), getString(R.string.purchases))
        sectionsPageAdapter.addFragment(BoughtFragment.newInstance(), getString(R.string.bought))
        viewPager.adapter = sectionsPageAdapter
    }
}

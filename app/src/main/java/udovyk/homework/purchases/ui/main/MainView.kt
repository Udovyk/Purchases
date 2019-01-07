package udovyk.homework.purchases.ui.main

import android.support.v4.view.ViewPager
import com.arellomobile.mvp.MvpView
import udovyk.homework.purchases.data.PurchaseEntity

interface MainView : MvpView {

    fun setUpViewPager(viewPager: ViewPager)

    fun addPurchaseToList(purchaseEntity: PurchaseEntity)

}
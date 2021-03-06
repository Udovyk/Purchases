package udovyk.homework.purchases.ui.main

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor() : BasePresenter<MainView>() {
    private val TAG = "MainPresenter"

    fun buyPurchase(purchaseEntity: PurchaseEntity) {
        disposable += dbManager
            .insertPurchase(purchaseEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { Log.e(TAG, it.message) })
    }

    fun buyAllPurchases(value: Boolean) {
        disposable += dbManager
            .updateAllPurchases(value)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { Log.e(TAG, it.message) })
    }
}
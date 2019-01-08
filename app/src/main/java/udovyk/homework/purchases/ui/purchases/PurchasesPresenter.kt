package udovyk.homework.purchases.ui.purchases

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class PurchasesPresenter @Inject constructor() : BasePresenter<PurchasesView>() {
    private val TAG = "MainPresenter"
    val purchasesLiveData: MutableLiveData<List<PurchaseEntity>> = MutableLiveData()

    fun getAllPurchases() {
        disposable += dbManager
            .getAllPurchases()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Test", "getBought inside call ")

                purchasesLiveData.postValue(it)

                //todo handle exceptions
            }
    }

    fun updateIsPurchaseOn(isPurchaseOn: Boolean, id: Int) {
        disposable += dbManager
            .updateIsPurchaseOnById(isPurchaseOn, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, { Log.e(TAG, it.message) })
        Log.d("Test", " MainPresenter addPurchase, insertPurchase")
    }
}


package udovyk.homework.purchases.ui.purchases

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class PurchasesPresenter @Inject constructor(val context: Context) : BasePresenter<PurchasesView>()  {

    val purchasesLiveData : MutableLiveData<List<PurchaseEntity>> = MutableLiveData()

    fun getAllPurchases() {
        disposable += dbManager
            .getAllPurchases()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Test", "getAllPurchases inside call ")

                purchasesLiveData.postValue(it)

                //todo handle exceptions
            }
    }

}
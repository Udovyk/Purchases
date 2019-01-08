package udovyk.homework.purchases.ui.bought

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.database.Observable
import android.util.Log
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import io.reactivex.Flowable.fromIterable
import io.reactivex.Observable.fromIterable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.toObservable
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BasePresenter
import udovyk.homework.purchases.ui.main.MainView
import java.util.stream.Collectors.toList
import javax.inject.Inject

@InjectViewState
class BoughtPresenter @Inject constructor() : BasePresenter<BoughtView>()  {

    val boughtLiveData : MutableLiveData<List<PurchaseEntity>> = MutableLiveData()

    fun getBought() {
        disposable += dbManager.getAllPurchases()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                val listBoughtOnly = it.filter { it.isBought == true }
                boughtLiveData.postValue(listBoughtOnly)

                //todo handle exceptions
            }
    }

}
package udovyk.homework.purchases.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import udovyk.homework.purchases.data.DbManager
import javax.inject.Inject

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    protected var disposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var dbManager: DbManager

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }


}
package udovyk.homework.purchases.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {

    protected var disposable: CompositeDisposable? = null

    override fun onDestroy() {
        super.onDestroy()
        dispose()
        disposable = null
    }

    private fun dispose() {
        disposable?.dispose()
    }
}
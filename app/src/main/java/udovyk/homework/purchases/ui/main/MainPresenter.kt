package udovyk.homework.purchases.ui.main

import android.content.Context
import android.widget.Toast
import com.arellomobile.mvp.InjectViewState
import udovyk.homework.purchases.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(val context: Context) : BasePresenter<MainView>() {

    fun test() {
         Toast.makeText(context, "MAIN test passed", Toast.LENGTH_SHORT).show()
    }
}
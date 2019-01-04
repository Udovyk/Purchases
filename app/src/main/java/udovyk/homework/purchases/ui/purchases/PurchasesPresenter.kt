package udovyk.homework.purchases.ui.purchases

import android.content.Context
import android.widget.Toast
import udovyk.homework.purchases.ui.base.BasePresenter
import javax.inject.Inject

class PurchasesPresenter @Inject constructor(val context: Context) : BasePresenter<PurchasesView>()  {

    fun test() {
        Toast.makeText(context, "PURCHASE test passed", Toast.LENGTH_SHORT).show()
    }
}
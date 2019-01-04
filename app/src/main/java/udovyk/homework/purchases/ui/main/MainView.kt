package udovyk.homework.purchases.ui.main

import android.support.v4.view.ViewPager
import com.arellomobile.mvp.MvpView

interface MainView : MvpView {

    fun setUpViewPager(viewPager: ViewPager)
}
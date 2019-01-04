package udovyk.homework.purchases.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.widget.TextView
import android.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import udovyk.homework.purchases.R
import udovyk.homework.purchases.ui.base.BaseActivity
import udovyk.homework.purchases.ui.bought.BoughtFragment
import udovyk.homework.purchases.ui.main.adapter.SectionsPageAdapter
import udovyk.homework.purchases.ui.purchases.PurchasesFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    companion object {
        const val TAG = "MainActivity"
    }

    @BindView(R.id.container)
    lateinit var viewPager: ViewPager

    @BindView(R.id.tabs)
    lateinit var tabLayout: TabLayout

    @Inject
    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenter

    val sectionsPageAdapter = SectionsPageAdapter(supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        presenter.viewState.setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

    }


    override fun setUpViewPager(viewPager: ViewPager) {
        sectionsPageAdapter.addFragment(PurchasesFragment.newInstance(), getString(R.string.purchases))
        sectionsPageAdapter.addFragment(BoughtFragment.newInstance(), getString(R.string.bought))
        viewPager.adapter = sectionsPageAdapter
    }
}

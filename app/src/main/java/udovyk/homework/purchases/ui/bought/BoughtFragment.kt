package udovyk.homework.purchases.ui.bought

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import udovyk.homework.purchases.R
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BaseFragment
import udovyk.homework.purchases.ui.bought.adapter.BoughtListAdapter
import javax.inject.Inject

class BoughtFragment : BaseFragment(), BoughtView {

    companion object {
        const val TAG = "BoughtFragment"
        fun newInstance() : BoughtFragment {
            val boughtFragment = BoughtFragment()
            return boughtFragment
        }
    }
    @BindView(R.id.rv_bought)
    lateinit var rvBought: RecyclerView

    @Inject
    @InjectPresenter
    lateinit var presenter: BoughtPresenter

    @ProvidePresenter
    fun providePresenter(): BoughtPresenter = presenter

    @Inject
    lateinit var adapter: BoughtListAdapter

    private val boughtObservable : Observer<List<PurchaseEntity>> = Observer {
        if (it != null && it.isNotEmpty()) {
            adapter.clear()
            adapter.addAll(it)
        } else adapter.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.boughtLiveData.observe(this, boughtObservable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        presenter.getBought()
    }

    private fun initRecyclerView() {
        rvBought.setHasFixedSize(false)
        rvBought.adapter = adapter
    }

    override fun getLayoutRes(): Int = R.layout.fragment_bought

}
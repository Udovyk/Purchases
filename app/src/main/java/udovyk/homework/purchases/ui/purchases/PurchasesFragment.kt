package udovyk.homework.purchases.ui.purchases

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import udovyk.homework.purchases.R
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BaseFragment
import udovyk.homework.purchases.ui.main.adapter.RecyclerTouchListener
import udovyk.homework.purchases.ui.purchases.adapter.PurchasesListAdapter
import javax.inject.Inject

class PurchasesFragment : BaseFragment(), PurchasesView {

    companion object {
        const val TAG = "PurchasesFragment"
        fun newInstance(): PurchasesFragment {
            val purchasesFragment = PurchasesFragment()
            return purchasesFragment
        }
    }

    @BindView(R.id.rv_purchases)
    lateinit var rvPurchases: RecyclerView

    @Inject
    @InjectPresenter
    lateinit var presenter: PurchasesPresenter

    @ProvidePresenter
    fun providePresenter(): PurchasesPresenter = presenter

    @Inject
    lateinit var adapter: PurchasesListAdapter

    @Inject
    lateinit var touchListener : RecyclerTouchListener

    private val purchasesObservable : Observer<List<PurchaseEntity>> = Observer {
        if (it != null && it.isNotEmpty()) {
            adapter.clear()
            adapter.addAll(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.purchasesLiveData.observe(this, purchasesObservable)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        presenter.getAllPurchases()
    }

    private fun initRecyclerView() {
        rvPurchases.setHasFixedSize(false)
        rvPurchases.adapter = adapter

        rvPurchases.addOnItemTouchListener(touchListener)
        touchListener.rv = rvPurchases

        touchListener.listener = object : RecyclerTouchListener.ClickListener {
            override fun onPress(position: Int, view: View) {
                val purchaseClicked = adapter.getData(position)
                val isBoughtNewValue = purchaseClicked.isBought != true
                presenter.updateIsPurchaseOn(isBoughtNewValue, purchaseClicked.id)
            }

            override fun onLongPress(position: Int, view: View) {
                //not implemented
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_purchases


}
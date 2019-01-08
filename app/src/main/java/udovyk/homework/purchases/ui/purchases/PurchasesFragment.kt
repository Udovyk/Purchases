package udovyk.homework.purchases.ui.purchases

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import udovyk.homework.purchases.R
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BaseFragment
import udovyk.homework.purchases.ui.main.adapter.RecyclerTouchListener
import udovyk.homework.purchases.ui.purchases.adapter.GridSpacingItemDecoration
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

    //todo inject later
    private val adapter = PurchasesListAdapter()

    //todo inject later
    @Inject
    lateinit var touchListener : RecyclerTouchListener

    val purchasesObservable : Observer<List<PurchaseEntity>> = Observer {

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

        //todo delete later


    }

    override fun updatePurchasesList() {
        presenter.getAllPurchases()
    }

    private fun initRecyclerView() {

        rvPurchases.setHasFixedSize(false)
        //todo GridSpacingItemDecoration
        //rvPurchases.addItemDecoration(GridSpacingItemDecoration(2, 5,  true))
        //rvPurchases.offsetChildrenVertical(5)
        //rvPurchases.offsetChildrenHorizontal(5)
        rvPurchases.layoutManager = GridLayoutManager(context, 2)
        rvPurchases.adapter = adapter

        rvPurchases.addOnItemTouchListener(touchListener)
        touchListener.rv = rvPurchases

        touchListener.listener = object : RecyclerTouchListener.ClickListener {
            override fun onPress(position: Int, view: View) {
                /*val uri = adapter.getData(position).imageUri

                Toast.makeText(activity, uri.toString(), Toast.LENGTH_SHORT).show()*/

                val purchaseClicked = adapter.getData(position)
                val isBoughtNewValue = purchaseClicked.isBought != true
                //dapter.setIsBought(isBoughtNewValue, position)
                //tvIconBought.visibility = if (purchaseClicked.isBought == false) View.VISIBLE else View.GONE
                Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()
                presenter.updateIsPurchaseOn(isBoughtNewValue, purchaseClicked.id)

            }

            override fun onLongPress(position: Int, view: View) {
                //not implemented yet
            }

        }


    }


    override fun getLayoutRes(): Int = R.layout.fragment_purchases


}
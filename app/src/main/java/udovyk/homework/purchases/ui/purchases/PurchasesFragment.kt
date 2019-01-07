package udovyk.homework.purchases.ui.purchases

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import udovyk.homework.purchases.R
import udovyk.homework.purchases.data.PurchaseEntity
import udovyk.homework.purchases.ui.base.BaseFragment
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

    private val adapter = PurchasesListAdapter()

    val purchasesObservable : Observer<List<PurchaseEntity>> = Observer {

        if (it != null && it.isNotEmpty()) {

            Log.d("Test", "purchasesObservable " + {if(it.size == 0) "fdf"  else it[0].imageUri.toString()} )
            adapter.clear()
            adapter.addAll(it)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        presenter.purchasesLiveData.observe(this, purchasesObservable)
    }
    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.purchasesLiveData.observe(this, purchasesObservable)
    }*/


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        /*val list = mutableListOf<PurchaseEntity>()
        list.add(PurchaseEntity(1, "content://media/external/images/media/48587", false))
        list.add(PurchaseEntity(2, "content://media/external/images/media/48587", false))
        list.add(PurchaseEntity(3, "content://media/external/images/media/48587", false))

        adapter.addAll(list)*/

            //updatePurchasesList()
        presenter.getAllPurchases()
    }

    override fun updatePurchasesList() {
        Log.d("Test", "updatePurchasesList ")
        presenter.getAllPurchases()
    }

    private fun initRecyclerView() {

        rvPurchases.setHasFixedSize(false)
        //todo GridSpacingItemDecoration
        //rvPurchases.addItemDecoration(GridSpacingItemDecoration(2, 5,  true))
        /*rvPurchases.offsetChildrenVertical(5)
        rvPurchases.offsetChildrenHorizontal(5)*/
        rvPurchases.layoutManager = GridLayoutManager(context, 2)

        rvPurchases.adapter = adapter

    }


    override fun getLayoutRes(): Int = R.layout.fragment_purchases


}
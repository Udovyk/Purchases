package udovyk.homework.purchases.ui.purchases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import udovyk.homework.purchases.R
import udovyk.homework.purchases.ui.base.BaseFragment
import javax.inject.Inject

class PurchasesFragment : BaseFragment(), PurchasesView {

    companion object {

        const val TAG = "PurchasesFragment"

        fun newInstance() : PurchasesFragment {
            val purchasesFragment = PurchasesFragment()

            return purchasesFragment
        }
    }

    @BindView(R.id.button2)
    lateinit var btn2 : Button

    @Inject
    @InjectPresenter
    lateinit var presenter: PurchasesPresenter

    @ProvidePresenter
    fun providePresenter(): PurchasesPresenter = presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn2.setOnClickListener {
            test()
        }
    }


    override fun getLayoutRes(): Int = R.layout.fragment_purchases

    override fun test() {
        presenter.test()
    }
}
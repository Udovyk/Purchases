package udovyk.homework.purchases.ui.bought

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

class BoughtFragment : BaseFragment(), BoughtView {

    companion object {
        const val TAG = "BoughtFragment"

        fun newInstance() : BoughtFragment {
            val boughtFragment = BoughtFragment()

            return boughtFragment
        }
    }


    /*@BindView(R.id.button1)
    lateinit var btn1 : Button*/

    @Inject
    @InjectPresenter
    lateinit var presenter: BoughtPresenter

    @ProvidePresenter
    fun providePresenter(): BoughtPresenter = presenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    override fun getLayoutRes(): Int = R.layout.fragment_bought

}
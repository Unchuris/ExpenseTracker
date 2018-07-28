package unchuris.vladislav.expensetracker.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_wallet.*
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.FragmentWalletBinding
import javax.inject.Inject
import unchuris.vladislav.expensetracker.ui.wallet.WalletListModel
import unchuris.vladislav.expensetracker.utils.autoCleared
import unchuris.vladislav.expensetracker.ui.chart.ChartListModel

class WalletFragment @Inject constructor() : DaggerFragment() {


    private lateinit var walletListModel: WalletListModel
    private lateinit var chartListModel: ChartListModel
    private var binding: FragmentWalletBinding by autoCleared()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_wallet,
                container,
                false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        walletListModel = ViewModelProviders.of(this).get(WalletListModel::class.java)
        binding.walletModel = walletListModel

//        mFragmentCardAdapter = CardFragmentPagerAdapter(childFragmentManager, dpToPixels(2, context))
//        viewPager.offscreenPageLimit = 5
        swipelayout.setOnRefreshListener { swipelayout.postDelayed({
            swipelayout.isRefreshing = false
        }, 3000) }

        chartListModel = ViewModelProviders.of(this).get(ChartListModel::class.java)
        binding.chartModel = chartListModel

        binding.setLifecycleOwner(this)
    }

    private fun dpToPixels(dp: Int, context: Context?): Float {
        return dp * context!!.resources.displayMetrics.density
    }
}

package unchuris.vladislav.expensetracker.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.FragmentBalanceBinding
import unchuris.vladislav.expensetracker.ui.money.MoneyListModel
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import unchuris.vladislav.expensetracker.utils.autoCleared
import javax.inject.Inject

class BalanceFragment @Inject constructor() : DaggerFragment() {

    private var binding: FragmentBalanceBinding by autoCleared<FragmentBalanceBinding>()
    private lateinit var viewModel: PostListViewModel
    private lateinit var moneyModel: MoneyListModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_balance,
                container,
                false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.postList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.moneyList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
        moneyModel = ViewModelProviders.of(this).get(MoneyListModel::class.java)
        binding.moneyModel = moneyModel

        binding.setLifecycleOwner(this)
    }
}

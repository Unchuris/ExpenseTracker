package unchuris.vladislav.expensetracker.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.Constraints.TAG
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_balance.*
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.FragmentBalanceBinding
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import unchuris.vladislav.expensetracker.utils.autoCleared

class BalanceFragment : DaggerFragment(), TransactionAddFragment.AddTransactionCallback {

    companion object {
        fun newInstance(): BalanceFragment {
            return BalanceFragment()
        }
    }

    private lateinit var transactionAddFragment: TransactionAddFragment

    override fun onTransactionCreated(transaction: Transaction) {
        viewModel.addNewTransaction(transaction)
    }

    private var binding: FragmentBalanceBinding by autoCleared()
    private lateinit var viewModel: PostListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_balance,
                container,
                false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add.setOnClickListener {
            addTransaction()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.postList.layoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
    }

    private fun addTransaction() {
        transactionAddFragment = TransactionAddFragment()
        transactionAddFragment.show(childFragmentManager, TAG)
    }
}

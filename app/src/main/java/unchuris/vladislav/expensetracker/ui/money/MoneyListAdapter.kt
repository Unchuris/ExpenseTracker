package unchuris.vladislav.expensetracker.ui.money

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.BalanceBinding
import unchuris.vladislav.expensetracker.model.Money

class MoneyListAdapter: RecyclerView.Adapter<MoneyListAdapter.ViewHolder>() {
    private var list: List<Money> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: BalanceBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.balance, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(postList: List<Money>) {
        this.list = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: BalanceBinding) : RecyclerView.ViewHolder(binding.root) {
        private val moneyModel = MoneyViewModel()

        fun bind(post: Money) {
            moneyModel.bind(post)
            binding.viewModel = moneyModel
        }
    }
}

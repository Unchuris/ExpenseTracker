package unchuris.vladislav.expensetracker.ui.transaction

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.databinding.ItemPostBinding
import unchuris.vladislav.expensetracker.model.Transaction

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList: List<Transaction>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<Transaction>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(post: Transaction) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}

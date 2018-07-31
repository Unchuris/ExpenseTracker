package unchuris.vladislav.expensetracker.ui.transaction

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.network.ITransactionApi
import unchuris.vladislav.expensetracker.repository.TransactionRepository
import javax.inject.Inject

class PostListViewModel : BaseViewModel() {

    @Inject
    lateinit var postApi: ITransactionApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val postListAdapter: PostListAdapter = PostListAdapter()

    companion object {
        private var listCurrentTransaction: MutableList<Transaction> = ArrayList()

        fun getListTransaction(): MutableList<Transaction> = listCurrentTransaction
    }

    init {
        if (listCurrentTransaction.isEmpty()) {
            val mockTransaction = TransactionRepository()
            subscription = mockTransaction.getAllTransactions()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { onRetrievePostListStart() }
                    .doOnTerminate { onRetrievePostListFinish() }
                    .subscribe(
                            { result -> onRetrievePostListSuccess(result) },
                            { onRetrievePostListError() }
                    )
        } else {
            postListAdapter.updatePostList(listCurrentTransaction)
        }
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Transaction>) {
        postListAdapter.updatePostList(postList as MutableList<Transaction>)
        listCurrentTransaction = postList
    }

    private fun onRetrievePostListError() {
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) {
            subscription.dispose()
        }
    }

    fun addNewTransaction(t: Transaction) {
        postListAdapter.addTransaction(t)
    }
}

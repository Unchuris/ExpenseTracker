package unchuris.vladislav.expensetracker.ui.money

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.network.TransactionApi
import unchuris.vladislav.expensetracker.repository.IMoneyRepository
import unchuris.vladislav.expensetracker.repository.MoneyRepository
import javax.inject.Inject

class MoneyListModel: BaseViewModel() {
    @Inject
    lateinit var postApi: TransactionApi


    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    var postMoneyAdapter = MoneyListAdapter()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        val mockMoney: IMoneyRepository = MoneyRepository()
        subscription = mockMoney.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) }
                ) { e -> onRetrievePostListError(e) }

    }

    private fun onRetrievePostListSuccess(postList: List<Money>) {
        postMoneyAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(e: Throwable) {
        e.printStackTrace()
        Log.d("ERR", e.message)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

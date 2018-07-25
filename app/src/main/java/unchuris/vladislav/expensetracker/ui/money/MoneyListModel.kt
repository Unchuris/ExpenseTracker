package unchuris.vladislav.expensetracker.ui.money

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.network.ITransactionApi
import unchuris.vladislav.expensetracker.repository.MoneyRepository
import javax.inject.Inject

class MoneyListModel : BaseViewModel() {
    @Inject
    lateinit var postApi: ITransactionApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    var postMoneyAdapter = MoneyListAdapter()

    init {
        val mockMoney: MoneyRepository = MoneyRepository()
        subscription = mockMoney.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) }
                ) { e -> onRetrievePostListError(e) }
    }

    private fun onRetrievePostListSuccess(postList: List<Money>) {
        postMoneyAdapter.updateList(postList)
    }

    private fun onRetrievePostListError(e: Throwable) {
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

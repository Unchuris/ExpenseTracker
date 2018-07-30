package unchuris.vladislav.expensetracker.ui.chart

import android.arch.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieEntry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.model.TransactionType
import unchuris.vladislav.expensetracker.repository.TransactionRepository
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import unchuris.vladislav.expensetracker.ui.wallet.WalletListModel
import unchuris.vladislav.expensetracker.utils.OperationUtils

class ChartListModel : BaseViewModel() {

    private lateinit var subscription: Disposable

    val chartData: MutableLiveData<PieData> = MutableLiveData()

    init {
        val data = PostListViewModel.getListTransaction()
        if (data.isEmpty()) {
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
            onRetrievePostListSuccess(data)
        }
    }

    private fun onRetrievePostListStart() {
    }

    private fun onRetrievePostListFinish() {
    }

    private fun onRetrievePostListSuccess(postList: List<Transaction>) {
        chartData.value = getPieData(postList)
    }

    private fun getPieData(postList: List<Transaction>): PieData {
        val ou = OperationUtils(WalletListModel.getRate())
        var sum = 0f
        val yEntrys = ArrayList<PieEntry>()
        enumValues<TransactionType>().forEach { category ->
            postList.filter { el ->
                (el.transactionType == category) and
                        (el.operationType == OperationType.SPEND) }.forEach { t ->
                sum += if (t.currency != Currency.DOLLAR) {
                    ou.convert(t.amount, t.currency, Currency.DOLLAR).toFloat()
                } else t.amount.toFloat()
            }

            if (sum > 0) {
                yEntrys.add(PieEntry(sum, category.name))
            }

            sum = 0f
        }

        return getChart(yEntrys)
    }

    private fun onRetrievePostListError() {
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) {
            subscription.dispose()
        }
    }
}

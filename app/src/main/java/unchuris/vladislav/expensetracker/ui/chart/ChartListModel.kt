package unchuris.vladislav.expensetracker.ui.chart

import android.arch.lifecycle.MutableLiveData
import android.graphics.Color
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.repository.TransactionRepository

class ChartListModel : BaseViewModel() {

    private lateinit var subscription: Disposable

    val chartData: MutableLiveData<PieData> = MutableLiveData()

    init {
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
    }

    private fun onRetrievePostListStart() {

    }

    private fun onRetrievePostListFinish() {

    }

    private fun onRetrievePostListSuccess(postList: List<Transaction>) {
        val hashMap = HashMap<String, Float>()
        for (i in 0 until postList.size) {
            hashMap[postList[i].transactionType.name] = postList[i].amount.toFloat()
        }
        val yEntrys = ArrayList<PieEntry>()
        val xEntrys = ArrayList<String>()
        var d = 0
        hashMap.forEach{
            yEntrys.add(PieEntry(it.value, d++))
            xEntrys.add(it.key)
        }
        val yData = floatArrayOf(10.0f, 10.0f, 60.00f, 5.00f, 5.00f, 3.00f, 7.0f)
        val yEntrys1 = ArrayList<PieEntry>()
        for (i in 0 until yData.size) {
            yEntrys1.add(PieEntry(yData[i], i))
        }
        chartData.value = getTransactions(yEntrys1, xEntrys)
    }

    private fun onRetrievePostListError() {
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun getTransactions(yEntrys: ArrayList<PieEntry>, xEntrys: ArrayList<String>): PieData {
        val pieDataSet = PieDataSet(yEntrys, "Employee Sales")
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueTextSize = 12f

        val colors = ArrayList<Int>()
        colors.add(Color.GRAY)
        colors.add(Color.BLUE)
        colors.add(Color.RED)
        colors.add(Color.GREEN)
        colors.add(Color.CYAN)
        colors.add(Color.YELLOW)
        colors.add(Color.MAGENTA)

        pieDataSet.colors = colors

        val pieData = PieData(pieDataSet)
        pieData.setValueFormatter(PercentFormatter())

        return pieData
    }
}
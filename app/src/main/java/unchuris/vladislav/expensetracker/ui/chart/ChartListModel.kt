package unchuris.vladislav.expensetracker.ui.chart

import android.arch.lifecycle.MutableLiveData
import android.graphics.Color
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import unchuris.vladislav.expensetracker.base.BaseViewModel

class ChartListModel : BaseViewModel() {

    val chartData: MutableLiveData<PieData> = MutableLiveData()

    init {
        chartData.value = getTransactions()
    }

    private fun getTransactions(): PieData {
        val yData = floatArrayOf(10.0f, 10.0f, 60.00f, 5.00f, 5.00f, 3.00f, 7.0f)
        val xData = arrayOf("Mitch", "Jessica", "Mohammad", "Kelsey", "Sam", "Robert", "Ashley")
        val yEntrys = ArrayList<PieEntry>()
        val xEntrys = ArrayList<String>()
        for (i in 0 until yData.size) {
            yEntrys.add(PieEntry(yData[i], i))
        }

        for (i in 1 until xData.size) {
            xEntrys.add(xData[i])
        }

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
package unchuris.vladislav.expensetracker.ui.transaction

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import unchuris.vladislav.expensetracker.base.BaseApplication.Companion.string
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Transaction
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
class PostViewModel : BaseViewModel() {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    private val date = MutableLiveData<String>()
    private val amount = MutableLiveData<String>()
    private val operationType = MutableLiveData<String>()
    private val currency = MutableLiveData<String>()

    fun bind(post: Transaction) {
        date.value = dateFormat.format(post.date)
        amount.value = String.format("%.2f", post.amount)
        operationType.value = post.operationType.toString()
        currency.value = string(post.currency.shortName)
    }

    fun getDate(): MutableLiveData<String> {
        return date
    }

    fun getAmount(): MutableLiveData<String> {
        return amount
    }

    fun getOperationType(): MutableLiveData<String> {
        return operationType
    }

    fun getCurrency(): MutableLiveData<String> {
        return currency
    }

}

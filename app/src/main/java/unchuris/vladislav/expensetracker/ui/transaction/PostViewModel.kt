package unchuris.vladislav.expensetracker.ui.transaction

import android.arch.lifecycle.MutableLiveData
import unchuris.vladislav.expensetracker.base.BaseApplication.Companion.string
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Transaction

class PostViewModel : BaseViewModel() {
    private val date = MutableLiveData<String>()
    private val amount = MutableLiveData<String>()
    private val operationType = MutableLiveData<String>()
    private val currency = MutableLiveData<String>()

    fun bind(post: Transaction) {
        date.value = post.date.toString()
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

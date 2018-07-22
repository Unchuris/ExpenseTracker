package unchuris.vladislav.expensetracker.ui.money

import android.arch.lifecycle.MutableLiveData
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Money

class MoneyViewModel: BaseViewModel() {
    private val type = MutableLiveData<String>()
    private val amount = MutableLiveData<String>()

    fun bind(post: Money){
        type.value = post.currency.shortName
        amount.value = post.value.toString()
    }

    fun getType(): MutableLiveData<String> {
        return type
    }

    fun getAmount(): MutableLiveData<String> {
        return amount
    }
}
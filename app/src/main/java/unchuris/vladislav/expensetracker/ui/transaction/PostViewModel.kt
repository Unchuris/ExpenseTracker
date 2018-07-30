package unchuris.vladislav.expensetracker.ui.transaction

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.base.BaseApplication.Companion.string
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.model.TransactionType
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
class PostViewModel : BaseViewModel() {

    private val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    private val date = MutableLiveData<String>()
    private val amount = MutableLiveData<String>()
    private val operationType = MutableLiveData<Int>()
    private val currency = MutableLiveData<String>()
    private val imgCategory = MutableLiveData<Int>()
    private val income = MutableLiveData<Boolean>()

    fun bind(post: Transaction) {
        date.value = dateFormat.format(post.date)
        amount.value = String.format("%.2f", post.amount)
        operationType.value = when (post.operationType) {
            OperationType.INCOME -> {
                income.value = false
                R.drawable.ic_arrow_upward_green_24dp
            }
            OperationType.SPEND -> {
                income.value = true
                R.drawable.ic_arrow_downward_red_24dp
            }
        }
        currency.value = string(post.currency.shortName)
        imgCategory.value = when (post.transactionType) {
            TransactionType.HOUSE -> R.drawable.ic_home_black_24dp
            TransactionType.RELAXATION -> R.drawable.ic_relax
            TransactionType.SERVICE -> R.drawable.ic_service
            TransactionType.SPORT -> R.drawable.ic_gym
            TransactionType.FOOD -> R.drawable.ic_food
            TransactionType.OTHER -> R.drawable.ic_other
            TransactionType.CLOTHES -> R.drawable.ic_clothes
        }
    }

    fun getDate(): MutableLiveData<String> = date

    fun getAmount(): MutableLiveData<String> = amount

    fun getOperationType(): MutableLiveData<Int> = operationType

    fun getCurrency(): MutableLiveData<String> = currency

    fun getImageCategory(): MutableLiveData<Int> = imgCategory

    fun getIncome(): MutableLiveData<Boolean> = income
}

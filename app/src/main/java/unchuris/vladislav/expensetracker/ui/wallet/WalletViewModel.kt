package unchuris.vladislav.expensetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import unchuris.vladislav.expensetracker.base.BaseApplication.Companion.string
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Wallet

class WalletViewModel : BaseViewModel() {

    private val typeWallet = MutableLiveData<String>()
    private val typeCurrency = MutableLiveData<String>()
    private val amount = MutableLiveData<String>()

    fun bind(post: Wallet) {
        typeWallet.value = post.type.toString()
        typeCurrency.value = string(post.money.currency.shortName)
        amount.value = String.format("%.2f", post.money.value) + " " + string(post.money.currency.shortName)
    }

    fun getTypeWallet(): MutableLiveData<String> {
        return typeWallet
    }

    fun getTypeCurrency(): MutableLiveData<String> {
        return typeCurrency
    }

    fun getAmount(): MutableLiveData<String> {
        return amount
    }
}

package unchuris.vladislav.expensetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.base.BaseApplication.Companion.string
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.model.WalletType

class WalletViewModel : BaseViewModel() {

    private val typeWallet = MutableLiveData<String>()
    private val typeCurrency = MutableLiveData<String>()
    private val amount = MutableLiveData<String>()
    private val icon = MutableLiveData<Int>()

    fun bind(post: Wallet) {
        typeWallet.value = string(when (post.type) {
            WalletType.CREDIT_CARD -> R.string.credit_card
            WalletType.CASH -> R.string.cash
            WalletType.BANK_ACCOUNT -> R.string.bank_account
        })
        typeCurrency.value = string(post.money.currency.shortName)
        amount.value = String.format("%.2f", post.money.value) + " " + string(post.money.currency.shortName)
        icon.value = when (post.type) {
            WalletType.BANK_ACCOUNT -> R.drawable.ic_account_balance_wallet_24dp
            WalletType.CASH -> R.drawable.ic_picture_in_cash_24dp
            WalletType.CREDIT_CARD -> R.drawable.ic_credit_card_24dp
            else -> R.drawable.ic_account_balance_wallet_24dp
        }
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

    fun getIcon(): MutableLiveData<Int> {
        return icon
    }
}

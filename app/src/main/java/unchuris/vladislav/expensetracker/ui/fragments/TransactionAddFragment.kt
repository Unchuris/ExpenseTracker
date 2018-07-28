package unchuris.vladislav.expensetracker.ui.fragments

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_transaction_add.*
import java.util.*
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.model.*
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.ui.wallet.WalletListModel

class TransactionAddFragment : DialogFragment() {

    interface AddTransactionCallback {
        fun onTransactionCreated(transaction: Transaction)
    }

    private lateinit var callback: AddTransactionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callback = parentFragment as AddTransactionCallback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_transaction_add, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_create_transaction.setOnClickListener {
            buildTransaction()
        }
        initializeSpinners()
    }

    private var mWallet: MutableList<Wallet> = ArrayList()

    private fun initializeSpinners() {
        spinner_transaction_category.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_list_item_1, context!!.resources.getStringArray(R.array.transaction_categories))
        spinner_transaction_currency.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_list_item_1, context!!.resources.getStringArray(R.array.currencies))
        val walletType = WalletListModel
        mWallet = walletType.getWallets()
        val type: Array<String> = Array(mWallet.size) { "" }
        var d = 0
        mWallet.forEach{
            type[d++] = it.type.name
        }
        spinner_transaction_wallet.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_list_item_1, type)
    }

    private fun buildTransaction() {
        val transactionCategory = when(spinner_transaction_category.selectedItem) {
            getStringRes(R.string.food)-> TransactionType.FOOD
            getStringRes(R.string.clothes)-> TransactionType.CLOTHES
            getStringRes(R.string.service)-> TransactionType.CLOTHES
            getStringRes(R.string.sport)-> TransactionType.SPORT
            getStringRes(R.string.house)-> TransactionType.HOUSE
            getStringRes(R.string.relaxation)-> TransactionType.RELAXATION
            getStringRes(R.string.other)-> TransactionType.OTHER
            else -> TransactionType.OTHER
        }

        val currency = when(spinner_transaction_currency.selectedItem) {
            getStringRes(R.string.rub) -> Currency.RUBLE
            getStringRes(R.string.usd) -> Currency.DOLLAR
            else -> Currency.RUBLE
        }

        val amount = et_transaction_sum.text.toString().toDouble()

        val chooseWallet = when(spinner_transaction_wallet.selectedItem) {
            WalletType.BANK_ACCOUNT.name -> mWallet.filter{it.type == WalletType.BANK_ACCOUNT}
            WalletType.CASH.name -> mWallet.filter{it.type == WalletType.CASH}
                WalletType.CREDIT_CARD.name -> mWallet.filter{it.type == WalletType.CREDIT_CARD}
            else -> mWallet.filter{it.type == WalletType.CASH}
        }

        val operationType = OperationType.SPEND
        val transaction = Transaction(2, Date(), operationType, transactionCategory, currency, amount, chooseWallet[0])
        callback.onTransactionCreated(transaction)
    }

    private fun getStringRes(resId: Int): String = context!!.resources.getString(resId)
}

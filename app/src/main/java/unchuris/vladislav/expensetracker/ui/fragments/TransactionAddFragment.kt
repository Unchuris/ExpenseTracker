package unchuris.vladislav.expensetracker.ui.fragments

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_transaction_add.*
import unchuris.vladislav.expensetracker.model.Transaction
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.functions.BiFunction
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.TransactionType
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.repository.WalletRepository
import java.util.*
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.R


class TransactionAddFragment : DialogFragment() {


    private lateinit var callback: AddTransactionCallback
    private lateinit var inputObserver: Disposable

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
            buildAndPassTransaction()
        }
        initializeSpinners()
    }

    private fun initializeSpinners() {
        spinner_transaction_category.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_list_item_1, context!!.resources.getStringArray(R.array.transaction_categories))
        spinner_transaction_currency.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_list_item_1, context!!.resources.getStringArray(R.array.currencies))
        spinner_transaction_wallet.adapter = ArrayAdapter<String>(context!!,
                android.R.layout.simple_list_item_1, context!!.resources.getStringArray(R.array.wallets))
    }



    interface AddTransactionCallback {
        fun onTransactionCreated(transaction: Transaction)
    }

    private fun buildAndPassTransaction() {
        val rep = WalletRepository()
        val wallets = rep.getWallets()

        var walletsList: List<Wallet> = emptyList()
        wallets.forEach {
            walletsList = it
        }
        val transaction = Transaction(23, Date(), OperationType.INCOME, TransactionType.HOUSE, Currency.RUBLE, 10.00, walletsList[0])
        callback.onTransactionCreated(transaction)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
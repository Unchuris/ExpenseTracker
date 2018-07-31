package unchuris.vladislav.expensetracker.repository

import io.reactivex.Observable
import unchuris.vladislav.expensetracker.model.TransactionType
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.model.Wallet
import java.util.Date

class TransactionRepository {

    fun getAllTransactions(): Observable<List<Transaction>> {
        val transactionsHardcode = ArrayList<Transaction>()

        val rep = WalletRepository()
        val wallets = rep.getWallets()

        var walletsList: List<Wallet> = emptyList()
        wallets.forEach {
            walletsList = it
        }

        val items = arrayListOf(
                Transaction(0, Date(), OperationType.SPEND, TransactionType.FOOD, Currency.DOLLAR, 10.00, walletsList[0]),
                Transaction(1, Date(), OperationType.INCOME, TransactionType.FOOD, Currency.DOLLAR, 200.00, walletsList[1]),
                Transaction(2, Date(), OperationType.SPEND, TransactionType.CLOTHES, Currency.DOLLAR, 70.00, walletsList[2]),
                Transaction(3, Date(), OperationType.INCOME, TransactionType.HOUSE, Currency.RUBLE, 100.00, walletsList[0]),
                Transaction(4, Date(), OperationType.SPEND, TransactionType.OTHER, Currency.RUBLE, 120.00, walletsList[0]),
                Transaction(5, Date(), OperationType.INCOME, TransactionType.RELAXATION, Currency.RUBLE, 102.00, walletsList[1]),
                Transaction(5, Date(), OperationType.INCOME, TransactionType.OTHER, Currency.DOLLAR, 723.10, walletsList[2]),
                Transaction(5, Date(), OperationType.INCOME, TransactionType.SERVICE, Currency.DOLLAR, 100.20, walletsList[1]),
                Transaction(5, Date(), OperationType.INCOME, TransactionType.SERVICE, Currency.RUBLE, 120.50, walletsList[1]),
                Transaction(5, Date(), OperationType.SPEND, TransactionType.RELAXATION, Currency.DOLLAR, 18.00, walletsList[1]),
                Transaction(5, Date(), OperationType.INCOME, TransactionType.SPORT, Currency.DOLLAR, 72.00, walletsList[0]),
                Transaction(5, Date(), OperationType.INCOME, TransactionType.SPORT, Currency.RUBLE, 12.00, walletsList[0])
        )

        transactionsHardcode.addAll(items)

        return Observable.just(transactionsHardcode)
    }

}

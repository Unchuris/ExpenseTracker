package unchuris.vladislav.expensetracker.repository

import io.reactivex.Observable
import unchuris.vladislav.expensetracker.model.*
import unchuris.vladislav.expensetracker.model.Currency
import java.util.*

class TransactionRepository {

    fun getAllTransactions(): Observable<List<Transaction>> {
        val transactionsHardcode = ArrayList<Transaction>()

        val rep = WalletRepository()
        val wallets = rep.getWallets()

        var walletsList: List<Wallet> = emptyList()
        wallets.forEach {
            walletsList = it
        }

        val item = Transaction(0, Date(), OperationType.SPEND, TransactionType.FOOD, Currency.DOLLAR, 1020.00, walletsList[0])
        val item2 = Transaction(1, Date(), OperationType.SPEND, TransactionType.FOOD, Currency.DOLLAR, 100.00, walletsList[1])
        val item3 = Transaction(2, Date(), OperationType.SPEND, TransactionType.CLOTHES, Currency.DOLLAR, 11230.00, walletsList[2])
        val item4 = Transaction(3, Date(), OperationType.INCOME, TransactionType.HOUSE, Currency.RUBLE, 10.00, walletsList[0])
        val item5 = Transaction(4, Date(), OperationType.SPEND, TransactionType.OTHER, Currency.RUBLE, 1230.00, walletsList[0])
        val item7 = Transaction(5, Date(), OperationType.SPEND, TransactionType.RELAXATION, Currency.RUBLE, 1022.00, walletsList[1])
        val item8 = Transaction(5, Date(), OperationType.SPEND, TransactionType.OTHER, Currency.DOLLAR, 673.10, walletsList[2])
        val item9 = Transaction(5, Date(), OperationType.INCOME, TransactionType.SERVICE, Currency.DOLLAR, 1000.20, walletsList[1])
        val item10 = Transaction(5, Date(), OperationType.INCOME, TransactionType.SERVICE, Currency.RUBLE, 120.50, walletsList[1])
        val item11 = Transaction(5, Date(), OperationType.SPEND, TransactionType.RELAXATION, Currency.DOLLAR, 1678.00, walletsList[1])
        val item12 = Transaction(5, Date(), OperationType.SPEND, TransactionType.SPORT, Currency.DOLLAR, 72.00, walletsList[0])
        val item13 = Transaction(5, Date(), OperationType.SPEND, TransactionType.SPORT, Currency.RUBLE, 12.00, walletsList[0])

        transactionsHardcode.add(item)
        transactionsHardcode.add(item2)
        transactionsHardcode.add(item3)
        transactionsHardcode.add(item4)
        transactionsHardcode.add(item5)
        transactionsHardcode.add(item7)
        transactionsHardcode.add(item8)
        transactionsHardcode.add(item9)
        transactionsHardcode.add(item10)
        transactionsHardcode.add(item11)
        transactionsHardcode.add(item12)
        transactionsHardcode.add(item13)

        return Observable.just(transactionsHardcode)
    }

    fun getAllTransactionType() {

    }
}

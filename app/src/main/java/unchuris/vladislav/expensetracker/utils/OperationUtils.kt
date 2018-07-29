package unchuris.vladislav.expensetracker.utils

import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.ui.wallet.RateModel

class OperationUtils(private val rateMap: HashMap<String, Double>) {

    fun sumOperations(transactions: List<Transaction>, currency: Currency): Double {
        var amount = 0.0
        var sum = 0.0
        for (transaction in transactions) {
            amount += if (currency == transaction.currency) transaction.amount else convert(transaction.amount, currency, transaction.currency)
            if (transaction.transactionType === OperationType.INCOME) sum += amount else sum -= amount
        }
        return sum
    }

    fun convert(amount: Double, fromCurrency: Currency, toCurrency: Currency): Double =
            amount * rate(fromCurrency, toCurrency)

    private fun rate(fromCurrency: Currency, toCurrency: Currency): Double {
        val r = rateMap[fromCurrency.standardName + "_" + toCurrency.standardName]
        return r ?: 0.0
    }
}

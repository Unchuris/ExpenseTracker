package unchuris.vladislav.expensetracker.model

import unchuris.vladislav.expensetracker.utils.EXCHANGE_RATE

data class MoneyOperation(val transaction: Transaction) {

    fun getDifference(): Double {
        val rubbles =
                when (transaction.currency) {
                    Currency.DOLLAR -> transaction.money * EXCHANGE_RATE
                    Currency.RUBLE -> transaction.money
                }

        return when (transaction.type) {
            OperationType.INCOME -> rubbles
            OperationType.SPEND -> -1 * rubbles
        }
    }
}

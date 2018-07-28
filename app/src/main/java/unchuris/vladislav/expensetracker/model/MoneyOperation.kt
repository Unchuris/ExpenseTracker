package unchuris.vladislav.expensetracker.model

import unchuris.vladislav.expensetracker.utils.EXCHANGE_RATE

data class MoneyOperation(val transaction: Transaction) {

    fun getDifference(): Double {
        val rubbles =
                when (transaction.currency) {
                    Currency.DOLLAR -> transaction.amount * EXCHANGE_RATE
                    Currency.RUBLE -> transaction.amount
                }

        return when (transaction.operationType) {
            OperationType.INCOME -> rubbles
            OperationType.SPEND -> -1 * rubbles
        }
    }
}

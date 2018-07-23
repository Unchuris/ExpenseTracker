package unchuris.vladislav.expensetracker.model

data class Transaction(val id: Int,
                       val type: OperationType,
                       val currency: Currency,
                       val money: Double)

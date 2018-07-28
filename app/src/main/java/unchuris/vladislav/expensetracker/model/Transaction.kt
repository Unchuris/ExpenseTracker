package unchuris.vladislav.expensetracker.model
import java.util.Date

data class Transaction(
    val id: Int,
    val date: Date,
    val operationType: OperationType,
    val transactionType: TransactionType,
    val currency: Currency,
    val amount: Double,
    val wallet: Wallet
)

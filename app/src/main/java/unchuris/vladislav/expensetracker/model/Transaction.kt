package unchuris.vladislav.expensetracker.model

//data class Transaction(val id: Int, val type: String, val money: Double)
data class Transaction(val userId: Int, val id: Int, val title: String, val body: String)

package unchuris.vladislav.expensetracker.model

enum class Currency constructor(val title: String, val symbol: String, val shortName: String) {
    RUBLE("рубль", "₽", "RUB"),
    DOLLAR("доллар", "$", "USD")
}
package unchuris.vladislav.expensetracker.model

import unchuris.vladislav.expensetracker.R

enum class Currency constructor(val title: Int, val symbol: String, val shortName: Int, val standardName: String) {
    RUBLE(R.string.rub, "₽", R.string.rubShort, "RUB"),
    DOLLAR(R.string.usd, "$", R.string.usdShort, "USD")
}

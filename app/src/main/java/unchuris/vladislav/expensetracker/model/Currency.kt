package unchuris.vladislav.expensetracker.model

import unchuris.vladislav.expensetracker.R

enum class Currency constructor(val title: Int, val symbol: String, val shortName: Int) {
    RUBLE(R.string.rub, "₽", R.string.rubShort),
    DOLLAR(R.string.usd, "$", R.string.usdShort)
}

package unchuris.vladislav.expensetracker.utils

import unchuris.vladislav.expensetracker.model.Currency

fun Double.toRubble(currency: Currency): Double {
    return when (currency) {
        Currency.RUBLE -> this
        Currency.DOLLAR -> this * EXCHANGE_RATE
    }
}

fun Double.toDollar(currency: Currency): Double {
    return when (currency) {
        Currency.RUBLE -> this / EXCHANGE_RATE
        Currency.DOLLAR -> this
    }
}

package unchuris.vladislav.expensetracker.utils

import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.MoneyOperation

fun sumOperations(operations: List<MoneyOperation>, currency: Currency): Double {
    var sumInRubbles = 0.0

    for (operation in operations) {
        sumInRubbles += operation.getDifference()
    }

    return when (currency) {
        Currency.DOLLAR -> sumInRubbles.toDollar(Currency.RUBLE)
        Currency.RUBLE -> sumInRubbles
    }
}

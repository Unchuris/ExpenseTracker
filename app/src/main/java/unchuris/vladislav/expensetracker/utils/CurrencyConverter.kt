package unchuris.vladislav.expensetracker.utils

interface CurrencyConverter {
    fun convert(currencyFrom: String, currencyTo: String): Double
}

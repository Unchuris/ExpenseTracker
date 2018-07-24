package unchuris.vladislav.expensetracker.utils

interface ICurrencyConverter {
    fun convert(currencyFrom: String, currencyTo: String): Double
}

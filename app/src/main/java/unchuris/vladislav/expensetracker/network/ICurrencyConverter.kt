package unchuris.vladislav.expensetracker.network

interface ICurrencyConverter {

    fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double

    fun rate(fromCurrency: String, toCurrency: String): Double

    fun currencies(): List<CurrencyData>
}
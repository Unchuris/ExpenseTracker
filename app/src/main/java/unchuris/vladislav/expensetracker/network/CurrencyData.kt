package unchuris.vladislav.expensetracker.network

class CurrencyData internal constructor(internal val id: String, private val currencyName: String, private val currencySymbol: String) {

    override fun toString(): String {
        return "Валюта: $id. Название: $currencyName. Символ: $currencySymbol"
    }
}

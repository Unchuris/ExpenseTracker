package unchuris.vladislav.expensetracker.network
//import java.io.IOException
//import javax.inject.Inject
//
//class CurrencyConverterApi : ICurrencyConverter {
//
//    @Inject
//    lateinit var postApi: ITransactionApi
//
//    override fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double {
//        return amount * rate(fromCurrency, toCurrency)
//    }
//
//    @Throws(RuntimeException::class)
//    override fun rate(fromCurrency: String, toCurrency: String): Double {
//        //val currencyConverterApiClient = CurrencyConverterApiClient.retrofit.create(CurrencyConverterApiClient::class.java)
//        val call = postApi.getRate(fromCurrency + "_" + toCurrency)
//        try {
//            val response = call.execute()
//            // body = response.body()
//            //val rate = body.get(fromCurrency + "_" + toCurrency)
//            return rate.getVal()
//        } catch (e: IOException) {
//            throw RuntimeException(e)
//        } catch (e: NullPointerException) {
//            throw RuntimeException("CURRENCY PAIR NOT FOUND")
//        }
//
//    }
//
//}

package unchuris.vladislav.expensetracker.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ICurrencyConverterApiClient {

    @GET("convert/?compact=y")
    fun getRate(@Query("q") pair: String): Observable<Map<String, RateValue>>

    @GET("currencies")
    fun getList(): Observable<Map<String, Map<String, CurrencyData>>>
}

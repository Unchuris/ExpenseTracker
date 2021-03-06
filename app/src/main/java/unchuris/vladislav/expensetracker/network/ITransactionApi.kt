package unchuris.vladislav.expensetracker.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.model.Transaction

interface ITransactionApi {

    @GET("/posts")
    fun getTransactions(): Observable<List<Transaction>>

    @GET("/balance")
    fun getBalance(): Observable<List<Money>>

    @GET("convert/?compact=y")
    fun getRate(@Query("q") pair: String): Observable<Map<String, RateValue>>

    @GET("currencies")
    fun getList(): Observable<Map<String, Map<String, CurrencyData>>>
}

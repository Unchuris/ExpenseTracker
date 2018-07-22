package unchuris.vladislav.expensetracker.network

import io.reactivex.Observable
import retrofit2.http.GET
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.model.Transaction

interface TransactionApi {

    @GET("/posts")
    fun getTransactions(): Observable<List<Transaction>>

    @GET("/balance")
    fun getBalance(): Observable<List<Money>>
}
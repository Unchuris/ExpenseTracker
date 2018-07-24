package unchuris.vladislav.expensetracker.repository
import io.reactivex.Observable;
import unchuris.vladislav.expensetracker.model.Money

interface IMoneyRepository {

    fun getBalance(): Observable<List<Money>>
}

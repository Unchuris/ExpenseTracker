package unchuris.vladislav.expensetracker.repository
import io.reactivex.Observable;
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.Money

class MoneyRepository : IMoneyRepository {

    override fun getBalance(): Observable<List<Money>> {
        val currentBalance = 1000.00
        val exchangeRate = 63.47

        val balancesHardcode = ArrayList<Money>()
        val rub = Money(currentBalance, Currency.RUBLE)
        val usd = Money(currentBalance / exchangeRate, Currency.DOLLAR)

        balancesHardcode.add(rub)
        balancesHardcode.add(usd)
        return Observable.just(balancesHardcode)
    }

}
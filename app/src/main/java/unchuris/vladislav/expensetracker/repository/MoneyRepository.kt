package unchuris.vladislav.expensetracker.repository
import io.reactivex.Observable;
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.utils.EXCHANGE_RATE

class MoneyRepository : IMoneyRepository {

    override fun getBalance(): Observable<List<Money>> {
        val currentBalance = 1000.00

        val balancesHardcode = ArrayList<Money>()
        val rub = Money(currentBalance, Currency.RUBLE)
        val usd = Money(currentBalance / EXCHANGE_RATE, Currency.DOLLAR)

        balancesHardcode.add(rub)
        balancesHardcode.add(usd)
        return Observable.just(balancesHardcode)
    }

}
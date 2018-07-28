package unchuris.vladislav.expensetracker.repository
import io.reactivex.Observable
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.model.WalletType
import java.util.*

class WalletRepository {

    fun getWallets(): Observable<List<Wallet>> {
        val currentBalance = 1000.21345678976
        val currentBalance2 = 1210.21345678976
        val currentBalance3 = 121.213458976

        val walletsHardcode = ArrayList<Wallet>()
        val item = Wallet(UUID.randomUUID(), WalletType.BANK_ACCOUNT, Money(currentBalance, Currency.RUBLE))
        val item2 = Wallet(UUID.randomUUID(), WalletType.CASH, Money(currentBalance2, Currency.DOLLAR))
        val item3 = Wallet(UUID.randomUUID(), WalletType.CREDIT_CARD, Money(currentBalance3, Currency.DOLLAR))

        walletsHardcode.add(item)
        walletsHardcode.add(item2)
        walletsHardcode.add(item3)
        return Observable.just(walletsHardcode)
    }
}
package unchuris.vladislav.expensetracker.repository
import io.reactivex.Observable
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.model.WalletType
import java.util.UUID

class WalletRepository {

    fun getWallets(): Observable<List<Wallet>> {
        val currentBalance = 3888.78
        val currentBalance2 = 285.74
        val currentBalance3 = 653.10

        val walletsHardcode = ArrayList<Wallet>()
        val item = Wallet(0, UUID.randomUUID(), WalletType.BANK_ACCOUNT, Money(currentBalance, Currency.RUBLE))
        val item2 = Wallet(1, UUID.randomUUID(), WalletType.CASH, Money(currentBalance2, Currency.DOLLAR))
        val item3 = Wallet(2, UUID.randomUUID(), WalletType.CREDIT_CARD, Money(currentBalance3, Currency.DOLLAR))

        walletsHardcode.add(item)
        walletsHardcode.add(item2)
        walletsHardcode.add(item3)
        return Observable.just(walletsHardcode)
    }
}

package unchuris.vladislav.expensetracker.utils

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import unchuris.vladislav.expensetracker.model.*
import unchuris.vladislav.expensetracker.model.Currency
import java.util.*

class OperationTest {

    private lateinit var list: List<Transaction>
    private lateinit var wallets: List<Wallet>
    private val map: HashMap<String, Double> = HashMap()

    @Before
    fun setUp() {
        val currentBalance = 3888.78
        val currentBalance2 = 285.74
        val currentBalance3 = 653.10
        wallets = listOf(
                Wallet(0, UUID.randomUUID(), WalletType.BANK_ACCOUNT, Money(currentBalance, unchuris.vladislav.expensetracker.model.Currency.RUBLE)),
                Wallet(1, UUID.randomUUID(), WalletType.CASH, Money(currentBalance2, unchuris.vladislav.expensetracker.model.Currency.DOLLAR)),
                Wallet(2, UUID.randomUUID(), WalletType.CREDIT_CARD, Money(currentBalance3, unchuris.vladislav.expensetracker.model.Currency.DOLLAR))
        )
        list = listOf(
                Transaction(0, Date(), OperationType.SPEND, TransactionType.FOOD, unchuris.vladislav.expensetracker.model.Currency.DOLLAR, 24.40, wallets[0]),
                Transaction(1, Date(), OperationType.INCOME, TransactionType.FOOD, unchuris.vladislav.expensetracker.model.Currency.RUBLE, 2321.32, wallets[1]),
                Transaction(2, Date(), OperationType.SPEND, TransactionType.CLOTHES, unchuris.vladislav.expensetracker.model.Currency.DOLLAR, 52.20, wallets[2])
        )
        map["USD_RUB"] = 62.01
        map["RUB_USD"] = 0.01
    }

    @Test
    fun testDollarToRubleConvert() {
        val ou = OperationUtils(map)
        val rez:Double = 100 * 62.01
        val my:Double = ou.convert(100.00, Currency.DOLLAR, Currency.RUBLE)
        Assert.assertEquals(rez, my, 0.1)
    }

    @Test
    fun testRubleToDollarConvert() {
        val ou = OperationUtils(map)
        val rez:Double = 100 * 0.01
        val my:Double = ou.convert(100.00, Currency.RUBLE, Currency.DOLLAR)
        Assert.assertEquals(rez, my, 0.1)
    }

    @Test
    fun testRubleToDollarConvert2() {
        val ou = OperationUtils(map)
        val rez:Double = -100 * 0.01
        val my:Double = ou.convert(-100.00, Currency.RUBLE, Currency.DOLLAR)
        Assert.assertEquals(rez, my, 0.1)
    }

    @Test
    fun testRubleToDollarConvert3() {
        val ou = OperationUtils(map)
        val rez:Double = 0 * 0.01
        val my:Double = ou.convert(0.0, Currency.RUBLE, Currency.DOLLAR)
        Assert.assertEquals(rez, my, 0.1)
    }

    @Test
    fun testSUMtoRuble() {
        val ou = OperationUtils(map)
        val rez:Double = -24.40 * 62.01 + 2321.32 - 52.20 * 62.01
        val my:Double = ou.sumOperations(list, Currency.RUBLE)
        Assert.assertEquals(rez, my, 0.1)
    }

    @Test
    fun testSUMtoDollar() {
        val ou = OperationUtils(map)
        val rez:Double = -24.40 + 2321.32 * 0.01 - 52.20
        val my:Double = ou.sumOperations(list, Currency.DOLLAR)
        Assert.assertEquals(rez, my, 0.01)
    }

}

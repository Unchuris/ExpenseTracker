package unchuris.vladislav.expensetracker.utils

import org.junit.Assert
import org.junit.Test
import unchuris.vladislav.expensetracker.model.Currency
import unchuris.vladislav.expensetracker.model.MoneyOperation
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Transaction
import org.junit.Before

class OperationTest {

    private lateinit var money: DoubleArray

    private lateinit var list: List<MoneyOperation>
    @Before
    fun setUp() {
        money = doubleArrayOf(100.00, 200.00, 140.00, 110.00, 10.00)
        list = listOf(
                MoneyOperation(Transaction(1, OperationType.INCOME, Currency.DOLLAR, money[0])),
                MoneyOperation(Transaction(2, OperationType.SPEND, Currency.RUBLE, money[1])),
                MoneyOperation(Transaction(3, OperationType.SPEND, Currency.RUBLE, money[2])),
                MoneyOperation(Transaction(4, OperationType.INCOME, Currency.DOLLAR, money[3])),
                MoneyOperation(Transaction(5, OperationType.INCOME, Currency.DOLLAR, money[4]))
        )
    }

    @Test
    fun testSumOperationsRub() {
        val sum = sumOperations(list, Currency.RUBLE)
        val res = money[0].toRubble(Currency.DOLLAR) -
                money[1].toRubble(Currency.RUBLE) -
                money[2].toRubble(Currency.RUBLE) +
                money[3].toRubble(Currency.DOLLAR) +
                money[4].toRubble(Currency.DOLLAR)

        Assert.assertEquals(res, sum, 0.01)
    }

    @Test
    fun testSumOperationsDol() {
        val sum = sumOperations(list, Currency.DOLLAR)
        val res = money[0].toDollar(Currency.DOLLAR) -
                money[1].toDollar(Currency.RUBLE) -
                money[2].toDollar(Currency.RUBLE) +
                money[3].toDollar(Currency.DOLLAR) +
                money[4].toDollar(Currency.DOLLAR)

        Assert.assertEquals(res, sum, 0.01)
    }
}

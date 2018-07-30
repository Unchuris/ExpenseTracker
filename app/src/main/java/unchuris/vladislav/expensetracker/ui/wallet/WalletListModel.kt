package unchuris.vladislav.expensetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.network.ITransactionApi
import unchuris.vladislav.expensetracker.network.RateValue
import unchuris.vladislav.expensetracker.repository.WalletRepository
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import unchuris.vladislav.expensetracker.utils.OperationUtils
import java.util.ArrayList
import javax.inject.Inject

class WalletListModel : BaseViewModel() {

    @Inject
    lateinit var api: ITransactionApi

    private lateinit var subscription: Disposable
    private lateinit var subscriptionRate: Disposable

    var postWalletAdapter = WalletListAdapter()

    var rateMap: MutableLiveData<HashMap<String, Double>> = MutableLiveData()

    var rate: MutableLiveData<String> = MutableLiveData()

    private val mas = arrayListOf("USD_RUB", "RUB_USD")

    companion object {
        private var rateMapSave: HashMap<String, Double> = HashMap()

        private var wallets: MutableList<Wallet> = ArrayList()

        fun getWallets(): MutableList<Wallet> = wallets

        fun getRate(): HashMap<String, Double> = rateMapSave
    }

    init {
        rateMap.value = HashMap()
        if (wallets.isEmpty()) {
            val mockWallet: WalletRepository = WalletRepository()
            subscription = mockWallet.getWallets()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { result -> onRetrievePostListSuccess(result) }
                    ) { e -> onRetrievePostListError(e) }
        } else {
            update(getWallets())
        }
        initRate()
    }

    private fun initRate() {
        subscription = api.getRate(mas.toArray().joinToString(","))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { }
                .doOnTerminate { }
                .subscribe(
                        { result -> onRetrieveRateSuccess(result) },
                        { }
                )
    }

    private fun onRetrieveRateSuccess(call: Map<String, RateValue>) {
        mas.forEach {
            rateMap.value!![it] = call[it]!!.`val`
        }
        rateMap.postValue(rateMap.value)
        rate.value = String.format("%.2f", rateMap.value!!["USD_RUB"])
        rateMapSave = rateMap.value!!
    }

    private fun onRetrievePostListSuccess(postList: List<Wallet>) {
        postList.forEach {
            postWalletAdapter.addCardItem(it)
            wallets.add(it.id, it)
        }
    }

    fun update(postList: List<Wallet>) {
        val updateWallet: MutableList<Wallet> = ArrayList()
        val ou = OperationUtils(getRate())
        val transaction = PostListViewModel.getListTransaction()
        var sum = 0.0
        postList.forEach {
            transaction.filter { el ->
                el.wallet.type == it.type
            }.forEach { t ->
                sum = if (t.currency == it.money.currency) {
                    if (t.operationType == OperationType.INCOME) sum + t.amount else sum - t.amount
                } else {
                    if (t.operationType == OperationType.INCOME) sum + ou.convert(t.amount, t.currency, it.money.currency) else
                        sum - ou.convert(t.amount, t.currency, it.money.currency)
                }
            }
            it.money.value = sum
            sum = 0.0
            updateWallet.add(it.id, it)
        }
        postWalletAdapter.updateCardItem(updateWallet)
        wallets = updateWallet
    }

    private fun onRetrievePostListError(e: Throwable) {
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) subscription.dispose()
        if (::subscriptionRate.isInitialized) subscriptionRate.dispose()
    }
}

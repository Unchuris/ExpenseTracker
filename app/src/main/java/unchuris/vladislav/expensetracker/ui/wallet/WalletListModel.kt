package unchuris.vladislav.expensetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.OperationType
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.repository.WalletRepository
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import unchuris.vladislav.expensetracker.utils.OperationUtils
import java.util.ArrayList

class WalletListModel : BaseViewModel() {

    private lateinit var subscription: Disposable

    var postWalletAdapter = WalletListAdapter()

    var rateMap: MutableLiveData<HashMap<String, Double>> = MutableLiveData()

    companion object {
        private var wallets: MutableList<Wallet> = ArrayList()

        fun getWallets(): MutableList<Wallet> = wallets
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
    }

    private fun onRetrievePostListSuccess(postList: List<Wallet>) {
        postList.forEach {
            postWalletAdapter.addCardItem(it)
            wallets.add(it.id, it)
        }
    }

    fun update(postList: List<Wallet>) {
        val updateWallet:MutableList<Wallet> = ArrayList()
        val ou = OperationUtils(RateModel.getRate())
        val transaction = PostListViewModel.getListTransaction()
        var sum = 0.0
        postList.forEach {
            transaction.filter { el -> el.wallet.type == it.type
            }.forEach{t ->
                sum += if (t.currency == it.money.currency) {
                    t.amount
                } else {
                    ou.convert(t.amount, t.currency, it.money.currency)
                }
            }
            it.money.value = sum
            updateWallet.add(it.id, it)
        }
        postWalletAdapter.updateCardItem(updateWallet)
        wallets = updateWallet
    }

    fun setRate(map: HashMap<String, Double>) {
        rateMap.value = map
    }

    private fun onRetrievePostListError(e: Throwable) {

    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) {
            subscription.dispose()
        }
    }
}

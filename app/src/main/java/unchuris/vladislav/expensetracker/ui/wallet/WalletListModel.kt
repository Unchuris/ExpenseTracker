package unchuris.vladislav.expensetracker.ui.wallet

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_wallet.*
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Wallet
import unchuris.vladislav.expensetracker.repository.WalletRepository

class WalletListModel : BaseViewModel() {

    private lateinit var subscription: Disposable

    var postWalletAdapter = WalletListAdapter()

    init {
        val mockWallet: WalletRepository = WalletRepository()
        subscription = mockWallet.getWallets()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) }
                ) { e -> onRetrievePostListError(e) }
    }

    private fun onRetrievePostListSuccess(postList: List<Wallet>) {
        postList.forEach {
            postWalletAdapter.addCardItem(it)
        }
    }

    private fun onRetrievePostListError(e: Throwable) {
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

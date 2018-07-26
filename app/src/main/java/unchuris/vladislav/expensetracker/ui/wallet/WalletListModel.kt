package unchuris.vladislav.expensetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_wallet.*
import unchuris.vladislav.expensetracker.R
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Money
import unchuris.vladislav.expensetracker.network.ITransactionApi
import unchuris.vladislav.expensetracker.repository.MoneyRepository
import unchuris.vladislav.expensetracker.ui.money.MoneyListAdapter
import unchuris.vladislav.expensetracker.utils.ShadowTransformer
import unchuris.vladislav.expensetracker.utils.cardBalance.CardItem
import javax.inject.Inject

class WalletListModel : BaseViewModel(){

    private lateinit var subscription: Disposable

    var postWalletAdapter = WalletListAdapter()

    init {
        val mockWallet: MoneyRepository = MoneyRepository()
        subscription = mockWallet.getBalance()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) }
                ) { e -> onRetrievePostListError(e) }
    }

    private fun onRetrievePostListSuccess(postList: List<Money>) {
        postWalletAdapter.addCardItem(CardItem(R.string.title_1, R.string.text_1))
        postWalletAdapter.addCardItem(CardItem(R.string.title_2, R.string.text_1))
        postWalletAdapter.addCardItem(CardItem(R.string.title_3, R.string.title_3))
        postWalletAdapter.addCardItem(CardItem(R.string.title_4, R.string.text_1))
    }

    private fun onRetrievePostListError(e: Throwable) {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}

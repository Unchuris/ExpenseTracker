package unchuris.vladislav.expensetracker.ui.wallet

import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.network.ITransactionApi
import unchuris.vladislav.expensetracker.network.RateValue
import javax.inject.Inject

class RateModel: BaseViewModel() {

    @Inject
    lateinit var api: ITransactionApi

    private lateinit var subscription: Disposable

    val listRate: MutableLiveData<Int> = MutableLiveData()

    companion object {
        private val rateMap = HashMap<String, Double>()

        fun getRateMap(): HashMap<String, Double> = rateMap
    }

    private val mas = arrayListOf("USD_RUB","RUB_USD")

    init {
        subscription = api.getRate(mas.toArray().joinToString(","))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart() {

    }

    private fun onRetrievePostListFinish() {

    }

    private fun onRetrievePostListSuccess(call: Map<String, RateValue>) {
        mas.forEach{
            rateMap[it] = call[it]!!.`val`
        }
    }

    private fun onRetrievePostListError() {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
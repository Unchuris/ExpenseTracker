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

    val rateMap: MutableLiveData<HashMap<String, Double>> = MutableLiveData()

    private val mas = arrayListOf("USD_RUB","RUB_USD")

    companion object {
        private var rateMapSave: HashMap<String, Double> = HashMap()

        fun getRate(): HashMap<String, Double> = rateMapSave
    }

    init {
        rateMap.value = HashMap()
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
            rateMap.value!![it] = call[it]!!.`val`
        }
        rateMap.postValue(rateMap.value)
        rateMapSave = rateMap.value!!
    }

    private fun onRetrievePostListError() {
        rateMap.value = getRate()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
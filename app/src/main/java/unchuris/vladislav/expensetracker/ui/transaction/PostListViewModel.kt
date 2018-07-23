package unchuris.vladislav.expensetracker.ui.transaction

import android.arch.lifecycle.MutableLiveData
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import unchuris.vladislav.expensetracker.base.BaseViewModel
import unchuris.vladislav.expensetracker.model.Transaction
import unchuris.vladislav.expensetracker.network.TransactionApi
import javax.inject.Inject

class PostListViewModel: BaseViewModel(){
    @Inject
    lateinit var postApi: TransactionApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val postListAdapter: PostListAdapter = PostListAdapter()

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = postApi.getTransactions()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                        { result -> onRetrievePostListSuccess(result) },
                        { onRetrievePostListError() }
                )
    }

    private fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList:List<Transaction>){
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}

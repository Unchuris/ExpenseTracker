package unchuris.vladislav.expensetracker.base

import android.arch.lifecycle.ViewModel
import unchuris.vladislav.expensetracker.injection.component.DaggerViewModelInjector
import unchuris.vladislav.expensetracker.injection.component.ViewModelInjector
import unchuris.vladislav.expensetracker.injection.module.NetworkModule
import unchuris.vladislav.expensetracker.ui.money.MoneyListModel
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is MoneyListModel -> injector.inject(this)
        }
    }
}

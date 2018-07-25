package unchuris.vladislav.expensetracker.base

import android.arch.lifecycle.ViewModel
import unchuris.vladislav.expensetracker.injection.component.DaggerIViewModelInjector
import unchuris.vladislav.expensetracker.injection.component.IViewModelInjector
import unchuris.vladislav.expensetracker.injection.module.NetworkModule
import unchuris.vladislav.expensetracker.ui.money.MoneyListModel
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: IViewModelInjector = DaggerIViewModelInjector
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

package unchuris.vladislav.expensetracker.injection.component

import dagger.Component
import unchuris.vladislav.expensetracker.injection.module.NetworkModule
import unchuris.vladislav.expensetracker.ui.money.MoneyListModel
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface IViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)
    fun inject(moneyListModel: MoneyListModel)

    @Component.Builder
    interface Builder {
        fun build(): IViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
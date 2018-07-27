package unchuris.vladislav.expensetracker.injection.component

import dagger.Component
import unchuris.vladislav.expensetracker.injection.module.NetworkModule
import unchuris.vladislav.expensetracker.ui.transaction.PostListViewModel
import unchuris.vladislav.expensetracker.ui.wallet.WalletListModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface IViewModelInjector {

    fun inject(postListViewModel: PostListViewModel)
    fun inject(moneyListModel: WalletListModel)

    @Component.Builder
    interface Builder {
        fun build(): IViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}

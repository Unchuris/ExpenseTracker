package unchuris.vladislav.expensetracker.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import unchuris.vladislav.expensetracker.MainActivity
import unchuris.vladislav.expensetracker.ui.fragments.WalletFragment
import unchuris.vladislav.expensetracker.ui.fragments.BalanceFragment
import unchuris.vladislav.expensetracker.ui.fragments.SettingsFragment

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun settingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun aboutFragment(): WalletFragment

    @ContributesAndroidInjector
    abstract fun balanceFragment(): BalanceFragment
}

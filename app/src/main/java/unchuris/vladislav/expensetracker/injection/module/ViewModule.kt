package unchuris.vladislav.expensetracker.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import unchuris.vladislav.expensetracker.MainActivity
import unchuris.vladislav.expensetracker.ui.settings.SettingsFragment

@Module
abstract class ViewModule {

    @ContributesAndroidInjector
    abstract fun settingsFragment() : SettingsFragment

    @ContributesAndroidInjector
    abstract fun mainActivity() : MainActivity


//    @ContributesAndroidInjector
//    abstract fun getBalanceFragment() : BalanceFragment


}
package unchuris.vladislav.expensetracker.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import unchuris.vladislav.expensetracker.injection.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
                .builder()
                .create(this)
                .build()
    }
}
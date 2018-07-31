package unchuris.vladislav.expensetracker.base

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import unchuris.vladislav.expensetracker.injection.DaggerIAppComponent

class BaseApplication : DaggerApplication() {

    companion object {
        private lateinit var baseApplication: BaseApplication

        fun string(resId: Int): String = baseApplication.resources.getString(resId)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerIAppComponent
                .builder()
                .create(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        baseApplication = applicationContext as BaseApplication
    }

}

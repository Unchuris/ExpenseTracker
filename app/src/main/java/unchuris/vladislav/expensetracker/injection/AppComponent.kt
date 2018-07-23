package unchuris.vladislav.expensetracker.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import unchuris.vladislav.expensetracker.base.BaseApplication
import unchuris.vladislav.expensetracker.injection.module.ViewModule

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ViewModule::class
        ]
)
interface AppComponent: AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder
        fun build(): AppComponent
    }
}
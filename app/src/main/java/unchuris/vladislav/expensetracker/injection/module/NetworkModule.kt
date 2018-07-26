package unchuris.vladislav.expensetracker.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import unchuris.vladislav.expensetracker.network.ITransactionApi

@Module
@Suppress("unused")
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): ITransactionApi {
        return retrofit.create(ITransactionApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}

val BASE_URL: String = "Http://fragment_wallet.com"

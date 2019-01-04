package udovyk.homework.purchases.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import udovyk.homework.purchases.PurchasesApp
import udovyk.homework.purchases.di.module.ActivityBuilderModule
import udovyk.homework.purchases.di.module.AppModule
import udovyk.homework.purchases.di.module.FragmentBuilderModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<PurchasesApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
package udovyk.homework.purchases.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import udovyk.homework.purchases.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}
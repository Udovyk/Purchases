package udovyk.homework.purchases.di.module

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import udovyk.homework.purchases.ui.main.MainActivity
import udovyk.homework.purchases.ui.main.adapter.SectionsPageAdapter
import javax.inject.Singleton

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

/*    //todo add later di
    @Provides
    @Singleton
    @JvmStatic
    fun provideSectionsPageAdapter(): SectionsPageAdapter = */
}
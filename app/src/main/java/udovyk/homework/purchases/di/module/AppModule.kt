package udovyk.homework.purchases.di.module

import android.app.Application
import android.content.Context
import android.support.annotation.NonNull
import dagger.Module
import dagger.Provides
import udovyk.homework.purchases.ui.main.adapter.SectionsPageAdapter
import javax.inject.Singleton

@Module
internal object AppModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideContext(application: Application): Context = application



}
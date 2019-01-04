package udovyk.homework.purchases.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import udovyk.homework.purchases.ui.bought.BoughtFragment
import udovyk.homework.purchases.ui.purchases.PurchasesFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindPurchasesFragment(): PurchasesFragment

    @ContributesAndroidInjector
    abstract fun bindBoughtFragment(): BoughtFragment
}
package udovyk.homework.purchases

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import udovyk.homework.purchases.di.DaggerAppComponent

class PurchasesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .app(this)
            .build()
}
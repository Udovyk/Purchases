package udovyk.homework.purchases.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import udovyk.homework.purchases.data.AppDatabase
import udovyk.homework.purchases.data.PurchaseDao

@Module
class DbModule {
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "purchases_db")
                    .build()

    @Provides
    fun providesPurchaseDao(database: AppDatabase): PurchaseDao = database.purchaseDao()

}
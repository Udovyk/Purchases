package udovyk.homework.purchases.data

import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbManager @Inject constructor(private val purchaseDao: PurchaseDao) {

    val executor: Executor = Executors.newSingleThreadExecutor()

    fun insertPurchase(purchaseEntity: PurchaseEntity): Completable {
        return Completable.fromAction{
            purchaseDao.insertPurchase(purchaseEntity)
        }
    }

    fun insertAll(purchaseList: List<PurchaseEntity>) = purchaseDao.insertAll(purchaseList)

    fun getPurchaseById(id: Int): Flowable<PurchaseEntity> = purchaseDao.getPurchaseById(id)

    fun getCount(): Int = purchaseDao.getCount()

    fun getAllPurchases(): Flowable<List<PurchaseEntity>> = purchaseDao.getAllPurchases()

    fun deletePurchase(purchaseEntity: PurchaseEntity) = purchaseDao.deletePurchase(purchaseEntity)

    fun deletePurchaseById(id: Int) = purchaseDao.deletePurchaseById(id)

    fun deleteAll(): Int = purchaseDao.deleteAll()

    fun updateIsPurchaseOnById(isPurchaseOn: Boolean, id: Int) = purchaseDao.updateIsPurchaseBoughtById(isPurchaseOn, id)

    //todo add update all -> selected all
    /*fun updateAlarmById(alarmMinutes: String, alarmHours: String, isAlarmOn: Boolean, mondayCheck: Boolean, tuesdayCheck: Boolean, wednesdayCheck: Boolean, thursdayCheck: Boolean, fridayCheck: Boolean, saturdayCheck: Boolean, sundayCheck: Boolean, id: Int) {
        purchaseDao.updateAlarmById(alarmMinutes, alarmHours, isAlarmOn, mondayCheck, tuesdayCheck, wednesdayCheck, thursdayCheck, fridayCheck, saturdayCheck, sundayCheck, id)
    }*/
}
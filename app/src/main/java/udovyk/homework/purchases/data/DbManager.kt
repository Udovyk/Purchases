package udovyk.homework.purchases.data

import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbManager @Inject constructor(private val purchaseDao: PurchaseDao) {

    fun insertPurchase(purchaseEntity: PurchaseEntity): Completable {
        return Completable.fromAction {
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

    fun updateIsPurchaseOnById(isPurchaseOn: Boolean, id: Int): Completable {
        return Completable.fromAction {
            purchaseDao.updateIsPurchaseBoughtById(isPurchaseOn, id)
        }
    }

    fun updateAllPurchases(isPurchaseOn: Boolean): Completable {
        return Completable.fromAction {
            purchaseDao.updateAllPurchases(isPurchaseOn)
        }
    }
}
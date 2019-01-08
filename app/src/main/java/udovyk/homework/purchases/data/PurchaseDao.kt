package udovyk.homework.purchases.data

import android.arch.persistence.room.*
import io.reactivex.Flowable


@Dao
interface PurchaseDao {

    //insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPurchase(purchaseEntity: PurchaseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(purchaseList: List<PurchaseEntity>)

    //get
    @Query("SELECT * FROM purchases WHERE id = :id")
    fun getPurchaseById(id: Int): Flowable<PurchaseEntity>

    @Query("SELECT COUNT(*) FROM purchases")
    fun getCount(): Int

    @Query("SELECT * FROM purchases")
    fun getAllPurchases(): Flowable<List<PurchaseEntity>>

    //delete
    @Delete
    fun deletePurchase(purchaseEntity: PurchaseEntity)

    @Query("DELETE FROM purchases WHERE id = :id")
    fun deletePurchaseById(id: Int)

    @Query("DELETE FROM purchases")
    fun deleteAll(): Int

    //update
    @Query("UPDATE purchases SET isBought = :isBought WHERE id = :id")
    fun updateIsPurchaseBoughtById(isBought: Boolean, id: Int)

    @Query("UPDATE purchases SET isBought = :isBought")
    fun updateAllPurchases(isBought: Boolean)

    //todo add update all -> selected all


}
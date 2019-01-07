package udovyk.homework.purchases.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "purchases")
data class PurchaseEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var imageUri: String? = null,
    var isBought: Boolean = false
)

package kg.geektech.shoplistapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import kg.geektech.shoplistapp.domain.models.ShopItemDbModel

@Dao
interface ShopDao {

    @Query("SELECT * FROM shop_item")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("SELECT * FROM shop_item WHERE id=:shopItemId LIMIT 1 ")
    suspend fun getShopItem(shopItemId: Int): ShopItemDbModel

    @Query("DELETE  FROM shop_item WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId: Int)

    @Update
    suspend fun editShopItem(shopItemDbModel: ShopItemDbModel)

}
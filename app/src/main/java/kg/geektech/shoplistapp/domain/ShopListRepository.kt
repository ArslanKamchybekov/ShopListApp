package kg.geektech.shoplistapp.domain

import androidx.lifecycle.LiveData
import kg.geektech.shoplistapp.domain.models.ShopItem

interface ShopListRepository {

    suspend fun addShopItem(shopItem: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItem(id: Int): ShopItem
}
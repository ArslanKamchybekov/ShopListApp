package kg.geektech.shoplistapp.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun getShopList(): LiveData<List<ShopItem>>

    fun deleteShopItem(shopItem: ShopItem)

    fun editShopItem(id: Int)

    fun getShopItem(id: Int): ShopItem
}
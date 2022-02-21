package kg.geektech.shoplistapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kg.geektech.shoplistapp.domain.ShopItem
import kg.geektech.shoplistapp.domain.ShopListRepository

class ShopListRepositoryImpl : ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()
    private val shopListLD = MutableLiveData<List<ShopItem>>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(id: Int) {
        val shopId = shopList[id]
        shopId.enabled = true
        shopList[id] = shopId
        updateList()
    }

    override fun getShopItem(id: Int): ShopItem {
        return shopList[id]
    }
}
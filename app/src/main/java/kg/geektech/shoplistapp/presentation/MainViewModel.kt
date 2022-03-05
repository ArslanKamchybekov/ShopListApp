package kg.geektech.shoplistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kg.geektech.shoplistapp.data.repositories.ShopListRepositoryImpl
import kg.geektech.shoplistapp.domain.*
import kg.geektech.shoplistapp.domain.models.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val shopList = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem) {
        addShopItemUseCase.addShopItem(shopItem)
    }

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    fun deleteItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem) {
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }

    fun getShopItem(id: Int): ShopItem {
        return getShopItemUseCase.getShopItem(id)
    }
}
package kg.geektech.shoplistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.geektech.shoplistapp.data.repositories.ShopListRepositoryImpl
import kg.geektech.shoplistapp.domain.*
import kg.geektech.shoplistapp.domain.models.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl()
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    private val shopList = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    suspend fun deleteItem(shopItem: ShopItem) {
        viewModelScope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }
    }

    fun editShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newShopItem)
        }
    }

     suspend fun getShopItem(id: Int): ShopItem {
        return getShopItemUseCase.getShopItem(id)
    }
}
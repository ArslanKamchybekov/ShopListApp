package kg.geektech.shoplistapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kg.geektech.shoplistapp.domain.*
import kg.geektech.shoplistapp.domain.models.ShopItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addShopItemUseCase: AddShopItemUseCase,
    getShopListUseCase: GetShopListUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val editShopItemUseCase: EditShopItemUseCase,
    private val getShopItemUseCase: GetShopItemUseCase
) : ViewModel() {

    private val shopList = getShopListUseCase.getShopList()

    fun addShopItem(shopItem: ShopItem) {
        viewModelScope.launch {
            addShopItemUseCase.addShopItem(shopItem)
        }
    }

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    fun deleteItem(shopItem: ShopItem) {
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
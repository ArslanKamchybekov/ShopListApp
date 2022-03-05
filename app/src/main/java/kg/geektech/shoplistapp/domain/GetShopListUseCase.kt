package kg.geektech.shoplistapp.domain

import androidx.lifecycle.LiveData
import kg.geektech.shoplistapp.domain.models.ShopItem

class GetShopListUseCase(private val repository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return repository.getShopList()
    }
}
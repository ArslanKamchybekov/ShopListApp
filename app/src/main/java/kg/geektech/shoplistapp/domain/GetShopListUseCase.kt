package kg.geektech.shoplistapp.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val repository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return repository.getShopList()
    }
}
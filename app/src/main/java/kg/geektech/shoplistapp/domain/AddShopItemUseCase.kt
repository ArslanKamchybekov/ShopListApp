package kg.geektech.shoplistapp.domain

import kg.geektech.shoplistapp.domain.models.ShopItem

class AddShopItemUseCase(private val repository: ShopListRepository) {

    suspend fun addShopItem(shopItem: ShopItem) {
        repository.addShopItem(shopItem)
    }
}
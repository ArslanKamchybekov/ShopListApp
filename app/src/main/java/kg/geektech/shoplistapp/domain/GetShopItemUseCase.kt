package kg.geektech.shoplistapp.domain

import kg.geektech.shoplistapp.domain.models.ShopItem

class GetShopItemUseCase(private val repository: ShopListRepository) {
    fun getShopItem(id: Int): ShopItem {
        return repository.getShopItem(id)
    }
}
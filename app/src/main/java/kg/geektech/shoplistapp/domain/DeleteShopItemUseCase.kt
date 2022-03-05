package kg.geektech.shoplistapp.domain

import kg.geektech.shoplistapp.domain.models.ShopItem

class DeleteShopItemUseCase(private val repository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem)
    }
}
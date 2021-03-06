package kg.geektech.shoplistapp.domain

import kg.geektech.shoplistapp.domain.models.ShopItem

class EditShopItemUseCase (private val repository: ShopListRepository) {

    suspend fun editShopItem(shopItem: ShopItem){
        repository.editShopItem(shopItem)
    }
}
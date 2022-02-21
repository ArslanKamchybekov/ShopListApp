package kg.geektech.shoplistapp.domain

class EditShopItemUseCase (private val repository: ShopListRepository) {

    fun editShopItem(id: Int){
        repository.editShopItem(id)
    }
}
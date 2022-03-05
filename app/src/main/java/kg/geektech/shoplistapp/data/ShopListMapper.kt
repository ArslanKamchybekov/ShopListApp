package kg.geektech.shoplistapp.data

import kg.geektech.shoplistapp.domain.models.ShopItem
import kg.geektech.shoplistapp.domain.models.ShopItemDbModel

class ShopListMapper {

    fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDbModel {
        return ShopItemDbModel(
            shopItem.id,
            shopItem.name,
            shopItem.count,
            shopItem.enabled
        )
    }

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem {
        return ShopItem(
            shopItemDbModel.name,
            shopItemDbModel.count,
            shopItemDbModel.enabled,
            shopItemDbModel.id
        )
    }

    fun mapListDbModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> {
        return list.map { mapDbModelToEntity(it) }
    }
}
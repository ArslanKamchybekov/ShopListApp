package kg.geektech.shoplistapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kg.geektech.shoplistapp.App
import kg.geektech.shoplistapp.data.ShopListMapper
import kg.geektech.shoplistapp.domain.ShopListRepository
import kg.geektech.shoplistapp.domain.models.ShopItem

class ShopListRepositoryImpl : ShopListRepository {

    private val mapper = ShopListMapper()

    override fun addShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        App.dataBase.shopListDao().getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().deleteShopItem(shopItem.id)
    }

    override fun editShopItem(shopItem: ShopItem) {
        App.dataBase.shopListDao().deleteShopItem(shopItem.id)
        App.dataBase.shopListDao().addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopItem(id: Int): ShopItem {
        return mapper.mapDbModelToEntity(App.dataBase.shopListDao().getShopItem(id))
    }
    init {
        for (i in 1..20) {
            val item = ShopItem("Name $i", i, false)
            addShopItem(item)
        }
    }
}
package kg.geektech.shoplistapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kg.geektech.shoplistapp.data.ShopListMapper
import kg.geektech.shoplistapp.data.local.ShopDao
import kg.geektech.shoplistapp.domain.ShopListRepository
import kg.geektech.shoplistapp.domain.models.ShopItem
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(private val dao: ShopDao) : ShopListRepository {

    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        dao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        dao.getShopList()
    ) {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        dao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        dao.editShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(id: Int): ShopItem {
        return mapper.mapDbModelToEntity(dao.getShopItem(id))
    }
}
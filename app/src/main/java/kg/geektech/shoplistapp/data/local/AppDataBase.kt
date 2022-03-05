package kg.geektech.shoplistapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geektech.shoplistapp.domain.models.ShopItemDbModel

@Database(entities = [ShopItemDbModel::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun shopListDao(): ShopDao
}
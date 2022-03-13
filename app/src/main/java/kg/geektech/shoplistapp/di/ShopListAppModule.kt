package kg.geektech.shoplistapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kg.geektech.shoplistapp.data.local.AppDataBase
import kg.geektech.shoplistapp.data.repositories.ShopListRepositoryImpl
import kg.geektech.shoplistapp.domain.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShopListAppModule {

    @Provides
    @Singleton
    fun provideShopListRepository(dataBase: AppDataBase): ShopListRepository {
        return ShopListRepositoryImpl(dataBase.shopListDao())
    }

    @Provides
    @Singleton
    fun provideShopListDatabase(app: Application): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, "shop_list_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAddShopItemUseCase(repository: ShopListRepository): AddShopItemUseCase {
        return AddShopItemUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteShopItemUseCase(repository: ShopListRepository): DeleteShopItemUseCase {
        return DeleteShopItemUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideEditShopItemUseCase(repository: ShopListRepository): EditShopItemUseCase {
        return EditShopItemUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetShopItemUseCase(repository: ShopListRepository): GetShopItemUseCase {
        return GetShopItemUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetShopListUseCase(repository: ShopListRepository): GetShopListUseCase {
        return GetShopListUseCase(repository)
    }

}
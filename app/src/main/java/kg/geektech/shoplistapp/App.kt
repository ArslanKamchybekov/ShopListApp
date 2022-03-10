package kg.geektech.shoplistapp

import android.app.Application
import androidx.room.Room
import kg.geektech.shoplistapp.data.local.AppDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "dataBase"
        ).fallbackToDestructiveMigration().build()
    }
    companion object{
        lateinit var dataBase : AppDataBase
    }
}
package kg.geektech.shoplistapp

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import kg.geektech.shoplistapp.data.local.AppDataBase

@HiltAndroidApp
class App : Application()
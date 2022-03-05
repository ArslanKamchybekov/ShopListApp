package kg.geektech.shoplistapp.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.databinding.ActivityMainBinding
import kg.geektech.shoplistapp.domain.models.ShopItem
import kg.geektech.shoplistapp.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
            Log.e("TAG", "getShopLiveData : $it")
        }
    }

    private fun addItem(): ShopItem {
        Log.e("TAG", "addShopItem")
        val text = binding.etId.text.toString()
        return if (text.isNotEmpty()) {
            ShopItem("potato", 2, false)
        } else {
            ShopItem("potato", 2, false, text.toInt())
        }
    }
}
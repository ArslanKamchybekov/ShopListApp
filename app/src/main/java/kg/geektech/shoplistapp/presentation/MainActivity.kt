package kg.geektech.shoplistapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.databinding.ActivityMainBinding
import kg.geektech.shoplistapp.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
            Log.e("Tag", "getShopLiveData : $it")
        }
    }

    private fun initListeners() {
        binding.btnEdit.setOnClickListener {
            val text = binding.etId.text.toString()
            if (text.isNotEmpty()) {
                try {
                    viewModel.editShopItem(text.toInt())
                    Log.d("edit", "EditShopItem")
                } catch (e: Exception) {
                    Log.d("edit", "e: $e")
                }
            }
        }
        binding.btnGetItem.setOnClickListener {
            val text = binding.etId.text.toString()
            if (text.isNotEmpty()) {
                try {
                    val shopItem = viewModel.getShopItem(text.toInt())
                    Log.d("get", "GetShopItem: $shopItem")
                } catch (e: Exception) {
                    Log.d("get", "e: $e")
                }
            }
        }
        binding.btnCreate.setOnClickListener {
            viewModel.addShopItem(addItem())
        }
        binding.btnDelete.setOnClickListener {
            viewModel.deleteItem(ShopItem("potato", 2, false, binding.etId.text.toString().toInt()))
        }
    }

    private fun addItem(): ShopItem {
        Log.e("Tag", "addShopItem")
        val text = binding.etId.text.toString()
        return if (text.isNotEmpty()) {
            ShopItem("potato", 2, false)
        } else {
            ShopItem("potato", 2, false, text.toInt())
        }
    }
}
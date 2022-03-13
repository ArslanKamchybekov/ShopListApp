package kg.geektech.shoplistapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.databinding.ActivityDetailBinding
import kg.geektech.shoplistapp.domain.models.ShopItem
import kg.geektech.shoplistapp.presentation.MainViewModel
import kotlin.random.Random

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnAdd.setOnClickListener {
            viewModel.addShopItem(addShopItem() as ShopItem)
            finish()
        }
    }

    private fun addShopItem(): Any {
        val product = binding.etProduct.text.toString()
        val amount = binding.etAmount.text.toString()
        return if (product.isNotEmpty() && amount.isNotEmpty()) {
            ShopItem(product, amount.toInt(), false)
        } else {
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}

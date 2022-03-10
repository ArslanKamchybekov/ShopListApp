package kg.geektech.shoplistapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.databinding.ActivityDetailBinding
import kg.geektech.shoplistapp.domain.models.ShopItem
import kg.geektech.shoplistapp.presentation.MainViewModel
import kotlin.random.Random

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private val binding: ActivityDetailBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.btnAddRandom.setOnClickListener {
            viewModel.addShopItem(randomShopItem())
            finish()
        }
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

    private fun randomShopItem(): ShopItem {
        return ShopItem(
            getRandomString(5),
            (0..10).random(),
            Random.nextBoolean(),
            (0..20).random()
        )
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }


    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}

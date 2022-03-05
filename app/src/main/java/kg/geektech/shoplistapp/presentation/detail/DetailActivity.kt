package kg.geektech.shoplistapp.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kg.geektech.shoplistapp.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context, DetailActivity::class.java))
        }
    }
}

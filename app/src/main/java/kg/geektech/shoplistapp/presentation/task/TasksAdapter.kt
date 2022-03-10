package kg.geektech.shoplistapp.presentation.task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.presentation.callbacks.ShopItemDiffCallback
import kg.geektech.shoplistapp.domain.models.ShopItem

class TasksAdapter : ListAdapter<ShopItem, TasksAdapter.ViewHolder>(ShopItemDiffCallback()) {

    var onShopItemClick: ((ShopItem) -> Unit)? = null
    var onShopItemLongClick: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.shop_list_disable
            VIEW_TYPE_ENABLED -> R.layout.shop_list_enable
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.onBind(shopItem)
        holder.itemView.setOnClickListener {
            onShopItemClick?.invoke(shopItem)
        }
        holder.itemView.setOnLongClickListener {
            onShopItemLongClick?.invoke(shopItem)
            return@setOnLongClickListener true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!getItem(position).enabled) {
            VIEW_TYPE_DISABLED
        } else {
            VIEW_TYPE_ENABLED
        }
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(shopItem: ShopItem) {
            view.findViewById<TextView>(R.id.tv_name).text = shopItem.name
            view.findViewById<TextView>(R.id.tv_count).text = shopItem.count.toString()
        }
    }

    companion object {
        const val VIEW_TYPE_DISABLED = 100
        const val VIEW_TYPE_ENABLED = 101
    }
}


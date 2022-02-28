package kg.geektech.shoplistapp.presentation

import androidx.recyclerview.widget.DiffUtil
import kg.geektech.shoplistapp.domain.ShopItem

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean =
        (oldItem.id == newItem.id)

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean =
        (oldItem == newItem)
}
package kg.geektech.shoplistapp.presentation.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kg.geektech.shoplistapp.constants.Constants
import kg.geektech.shoplistapp.databinding.ShopListDisableBinding
import kg.geektech.shoplistapp.databinding.ShopListEnableBinding
import kg.geektech.shoplistapp.domain.ShopItem
import kg.geektech.shoplistapp.presentation.ShopItemDiffCallback

class TasksAdapter(private val longClick: (ShopItem) -> Unit) :
    ListAdapter<ShopItem, RecyclerView.ViewHolder>(ShopItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.DISABLE) {
            val binding =
                ShopListDisableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderDisable(binding) {
                longClick(currentList[it])
            }
        } else {
            val binding =
                ShopListEnableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderEnable(binding) {
                longClick(currentList[it])
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == Constants.DISABLE) {
            val viewHolderDisable = holder as ViewHolderDisable
            viewHolderDisable.onBind(getItem(position))
        } else {
            val viewHolderEnable = holder as ViewHolderEnable
            viewHolderEnable.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) {
            Constants.ENABLE
        } else {
            Constants.DISABLE
        }
    }

    class ViewHolderDisable(
        private val binding: ShopListDisableBinding,
        function: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                function(absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun onBind(shopItem: ShopItem) {
            binding.tvName.text = shopItem.name
            binding.tvCount.text = shopItem.count.toString()
        }
    }

    class ViewHolderEnable(
        private val binding: ShopListEnableBinding,
        function: (Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                function(absoluteAdapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun onBind(shopItem: ShopItem) {
            binding.tvName.text = shopItem.name
            binding.tvCount.text = shopItem.count.toString()
        }
    }
}

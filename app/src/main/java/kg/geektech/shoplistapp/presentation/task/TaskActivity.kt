package kg.geektech.shoplistapp.presentation.task

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.databinding.ActivityTaskBinding
import kg.geektech.shoplistapp.presentation.main.MainViewModel

class TaskActivity : AppCompatActivity(R.layout.activity_task) {

    private val binding: ActivityTaskBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()

    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
//            adapterTask.list = it
            tasksAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
//        adapterTask = TaskAdapter()
        tasksAdapter = TasksAdapter {
            viewModel.editShopItem(it.id)
            Toast.makeText(this, "id: ${it.id} , status: ${it.enabled}", Toast.LENGTH_SHORT).show()
        }
        binding.rvTask.apply {
            adapter = tasksAdapter
        }
        setUpSwipeListener(binding.rvTask)
    }

    private fun setUpSwipeListener(rvTask: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = adapterTask.list[viewHolder.absoluteAdapterPosition]
                val item = tasksAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvTask)
    }
}
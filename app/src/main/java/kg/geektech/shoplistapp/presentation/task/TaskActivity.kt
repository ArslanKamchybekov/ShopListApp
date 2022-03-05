package kg.geektech.shoplistapp.presentation.task

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geektech.shoplistapp.R
import kg.geektech.shoplistapp.databinding.ActivityTaskBinding
import kg.geektech.shoplistapp.presentation.detail.DetailActivity
import kg.geektech.shoplistapp.presentation.MainViewModel
import java.lang.RuntimeException

class TaskActivity : AppCompatActivity(R.layout.activity_task) {

    private val binding: ActivityTaskBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        initObservers()
        initListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.search_button)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Введите ID:"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.toInt()?.let {
                    try {
                        Toast.makeText(
                            this@TaskActivity,
                            "Info: " + viewModel.getShopItem(it).name + " id:" + viewModel.getShopItem(
                                it
                            ).id + " count: " + viewModel.getShopItem(it).count + " status: " + viewModel.getShopItem(
                                it
                            ).enabled,
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: RuntimeException) {
                        Toast.makeText(
                            this@TaskActivity,
                            e.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun initListeners() {
        tasksAdapter.onShopItemClick = {
            Toast.makeText(
                applicationContext,
                "id: ${it.id} status: ${it.enabled}",
                Toast.LENGTH_SHORT
            ).show()
        }
        tasksAdapter.onShopItemLongClick = {
            viewModel.editShopItem(it)
        }
        binding.btnFab.setOnClickListener {
            DetailActivity.start(this)
        }
    }

    private fun initObservers() {
        viewModel.getShopList().observe(this) {
            tasksAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        tasksAdapter = TasksAdapter()
        binding.rvTask.apply {
            adapter = this@TaskActivity.tasksAdapter
            setUpSwipeListener(this)
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
                val item = tasksAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvTask)
    }
}
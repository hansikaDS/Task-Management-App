package com.example.to_do

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.Adapter.ToDoAdapter
import com.example.to_do.Utils.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnDialogCloseListener {

    private lateinit var recview: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var db: AppDatabase
    private lateinit var adapter: ToDoAdapter

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recview = findViewById(R.id.recview)
        fab = findViewById(R.id.fab)
        adapter = ToDoAdapter(db.taskDao())

        db = AppDatabase.getDatabase(this)

        recview.layoutManager = LinearLayoutManager(this)
        recview.adapter = adapter

        fab.setOnClickListener {
            AddNewTask.newInstance().show(supportFragmentManager, AddNewTask.TAG)
        }

        GlobalScope.launch(Dispatchers.Main) {
            val tasks = db.taskDao().getAllTasks()
            adapter.setTasks(tasks)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onDialogClose(dialogInterface: DialogInterface) {
        GlobalScope.launch(Dispatchers.Main) {
            val tasks = db.taskDao().getAllTasks()
            adapter.setTasks(tasks)
        }
    }
}

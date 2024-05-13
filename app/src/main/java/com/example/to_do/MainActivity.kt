package com.example.to_do

import com.example.to_do.Adapter.ToDoAdapter
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.Model.ToDoModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), OnDialogCloseListener {

    private lateinit var recview: RecyclerView
    private lateinit var fab: FloatingActionButton


    private var mList: List<ToDoModel> = listOf()
    private lateinit var adapter: ToDoAdapter



    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recview = findViewById(R.id.recview)
        fab = findViewById(R.id.fab)


        mList = ArrayList()



        adapter.setTasks(mList)
        adapter.notifyDataSetChanged()

        recview.setHasFixedSize(true)
        recview.layoutManager = LinearLayoutManager(this)
        recview.adapter = adapter



        fab.setOnClickListener {
            AddNewTask.newInstance().show(supportFragmentManager, AddNewTask.TAG)
        }

        val itemTouchHelper = ItemTouchHelper(RecyclerViewTouchHelper(adapter))
        itemTouchHelper.attachToRecyclerView(recview)


    }

    override fun onDialogClose(dialogInterface: DialogInterface) {

        adapter.setTasks(mList)
        adapter.notifyDataSetChanged()
    }

}
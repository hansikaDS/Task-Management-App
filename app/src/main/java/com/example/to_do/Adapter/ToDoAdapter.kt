package com.example.to_do.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.Model.Task
import com.example.to_do.R
import com.example.to_do.TaskDao
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ToDoAdapter(private val taskDao: TaskDao) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    private var mList: List<Task> = listOf()
    private lateinit var context: Context

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        context = parent.context
        return MyViewHolder(v)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = mList[position]
        holder.checkBox.text = item.name
        holder.checkBox.isChecked = item.status != 0
        holder.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            GlobalScope.launch(Dispatchers.IO) {
                if (isChecked) {
                    taskDao.updateTask(item.copy(status = 1))
                } else {
                    taskDao.updateTask(item.copy(status = 0))
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(tasks: List<Task>) {
        mList = tasks
        notifyDataSetChanged()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun deleteTask(position: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(mList[position])
        }
    }



    fun getContext(): Context {
        return context
    }
}

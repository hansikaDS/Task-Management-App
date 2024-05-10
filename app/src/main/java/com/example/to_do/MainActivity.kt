package com.example.to_do

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do.Utils.DataBaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recview: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var myDB: DataBaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recview = findViewById(R.id.recview)
        fab = findViewById(R.id.fab)
        myDB = DataBaseHelper(this@MainActivity)

        fab.setOnClickListener {
            
        }




    }
}
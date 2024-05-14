package com.example.to_do.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val status: Any,
    val description: String,
    val priority: Int,
    val deadline: Long
)

package com.example.arrows.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    val userId: Int,

    @ColumnInfo(name = "nazov_hraca")
    val meno: String,

    @ColumnInfo(name = "score")
    val score: Int
)
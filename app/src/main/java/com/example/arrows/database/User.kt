package com.example.arrows.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "uzivatel")
data class User(
    @PrimaryKey(autoGenerate = true)
    var uzivatelId: Long = 0L,

    @ColumnInfo(name = "meno")
    var meno: String,

    @ColumnInfo(name = "score")
    var score: Int
)
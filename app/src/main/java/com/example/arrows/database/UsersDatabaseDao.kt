package com.example.arrows.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDatabaseDao {

    @Insert
    suspend fun insert(uzivatel: User)

    @Update
    suspend fun update(uzivatel: User)

    @Query("SELECT * FROM uzivatel WHERE uzivatelId = :key")
    suspend fun get(key: Long): User

    @Query("DELETE FROM uzivatel")
    suspend fun clear()

    @Query("SELECT * FROM uzivatel ORDER BY uzivatelId DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM uzivatel ORDER BY uzivatelId DESC LIMIT 1")
    suspend fun getTonight(): User?
}
package com.example.arrows.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("DELETE FROM user")
    suspend fun clear()

    @Query("SELECT * from user WHERE userId = :key")
    suspend fun get(key: Int): User

    @Query("SELECT nazov_hraca FROM user WHERE nazov_hraca = :key")
    suspend fun meno(key: String): String

    @Query("SELECT score FROM user WHERE userId = :key")
    suspend fun score(key: Int): Int

    @Query("SELECT max(userId) FROM user")
    suspend fun maxId(): Int

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>
}
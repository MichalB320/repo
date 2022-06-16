package com.example.arrows.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Definuje metódy pre používanie s databázou
 */
@Dao
interface UserDatabaseDao {

    /**
     * Vloží do datbázy nového používateľa
     *
     * @param user
     */
    @Insert
    suspend fun insert(user: User)

    /**
     * Aktualizuje užívateľa v databáze
     *
     * @param user
     */
    @Update
    suspend fun update(user: User)

    /**
     * Vymaže databázu
     */
    @Query("DELETE FROM user")
    suspend fun clear()

//    /**
//     *
//     */
//    @Query("SELECT * from user WHERE userId = :key")
//    suspend fun get(key: Int): User

    /**
     * Vráti používateľa z databázy
     *
     * @param meno
     * @return User
     */
    @Query("SELECT * FROM user WHERE nazov_hraca = :meno")
    suspend fun getUser(meno: String): User

    /**
     * Vráti meno používateľa z databázy
     *
     * @param key
     * @return String
     */
    @Query("SELECT nazov_hraca FROM user WHERE nazov_hraca = :key")
    suspend fun meno(key: String): String

    /**
     * Vráti maximálne ID v databáze
     *
     * @return Int
     */
    @Query("SELECT max(userId) FROM user")
    suspend fun maxId(): Int

    /**
     * Vráti počet používateľov v databáze
     *
     * @return Int
     */
    @Query("SELECT count(userId) FROM user")
    suspend fun countUser(): Int

    /**
     * Vráti všetkých užívatelov z databázy
     *
     * @return LiveData<List<User>>
     */
    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>
}
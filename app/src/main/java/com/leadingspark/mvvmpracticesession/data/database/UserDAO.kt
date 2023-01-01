package com.leadingspark.mvvmpracticesession.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leadingspark.mvvmpracticesession.data.database.entities.CURRENT_USER_ID
import com.leadingspark.mvvmpracticesession.data.database.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun upsert(user: User): Long

    @Query("SELECT * FROM user WHERE uid=$CURRENT_USER_ID")
    fun getUser(): LiveData<User>
}
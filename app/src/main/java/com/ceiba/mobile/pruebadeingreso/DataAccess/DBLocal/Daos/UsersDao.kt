package com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.example.quenta.DataAccess.DBLocal.Daos.BaseDao

@Dao
abstract class UsersDao : BaseDao<Users> {
    @Query("SELECT * FROM Users")
    abstract fun getUsers(): LiveData<List<Users>>

    @Query("DELETE FROM Users")
    abstract fun nukeUsers()
}
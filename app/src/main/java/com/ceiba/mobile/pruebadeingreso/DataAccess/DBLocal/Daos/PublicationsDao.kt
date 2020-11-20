package com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications
import com.example.quenta.DataAccess.DBLocal.Daos.BaseDao

@Dao
abstract class PublicationsDao : BaseDao<Publications> {
    @Query("SELECT * FROM Publications")
    abstract fun getPublications(): LiveData<List<Publications>>

    @Query("SELECT * FROM Publications P WHERE P.UserID = :id")
    abstract fun getPublicationsForUserID(id: Int): LiveData<List<Publications>>

    @Query("DELETE FROM Publications")
    abstract fun nukePublications()
}
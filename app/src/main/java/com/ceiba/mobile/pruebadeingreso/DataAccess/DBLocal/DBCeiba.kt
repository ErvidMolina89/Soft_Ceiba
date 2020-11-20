package com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.Daos.*
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.*

@Database(entities = arrayOf(
    Users::class,
    Publications::class
), version = 1)
abstract class DBCeiba : RoomDatabase() {

    abstract fun usersDao(): UsersDao
    abstract fun publicationsDao(): PublicationsDao

    companion object {
        private const val nameDB = "CeibaDB"
        @Volatile
        private var INSTANCE: DBCeiba? = null

        fun getInstance(context: Context): DBCeiba =
                INSTANCE ?: synchronized(this) {
                    buildDatabase(context).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(context: Context) =
                Room
                    .databaseBuilder(context.applicationContext, DBCeiba::class.java, nameDB)
                    .build()
    }
}
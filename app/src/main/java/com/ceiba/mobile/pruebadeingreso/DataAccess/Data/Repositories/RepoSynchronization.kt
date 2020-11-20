package com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories

import android.content.Context
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.DBCeiba
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RepoSynchronization {

    fun onInsertUsers(context: Context, usersList: MutableList<Users>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBCeiba.getInstance(context).usersDao().insertList(usersList))
        }
    }

    fun onInsertPublications(context: Context, items: MutableList<Publications>, onSuccessInsert: (List<Long>) -> Unit = {}){
        GlobalScope.launch {
            onSuccessInsert(DBCeiba.getInstance(context).publicationsDao().insertList(items))
        }
    }

    fun deleteAllUsers(context: Context) =
        GlobalScope.launch {
            DBCeiba.getInstance(context).usersDao().nukeUsers()
        }

    fun deleteAllPublications(context: Context) =
        GlobalScope.launch {
            DBCeiba.getInstance(context).publicationsDao().nukePublications()
        }

    fun getAllUsers(context: Context)
            = DBCeiba.getInstance(context).usersDao().getUsers()

    fun getPublicationsForUserID(context: Context, id: Int)
            = DBCeiba.getInstance(context).publicationsDao().getPublicationsForUserID(id)

    fun getAllPublications(context: Context)
        = DBCeiba.getInstance(context).publicationsDao().getPublications()
    }
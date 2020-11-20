package com.ceiba.mobile.pruebadeingreso.DataAccess.Data.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories.RepoPostID
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories.RepoSynchronization

class PostViewModel : ViewModel() {
    private val postRepository = RepoPostID()
    private val synchronization = RepoSynchronization()
    val user : MutableLiveData<Users> = MutableLiveData()
    val Publications : MutableLiveData<MutableList<Publications>> = postRepository.getPublications()

    fun getListPublications(){
        synchronization.getPublicationsForUserID(App.mContext!!, user.value?.id!!).observeForever {
            if (it?.size != 0) {
                Publications.value = (it as MutableList<Publications>)
            } else {
                postRepository.CallService(user.value?.id!!)
            }
        }
    }
}
package com.ceiba.mobile.pruebadeingreso.DataAccess.Data.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.Base.BaseActivity
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories.RepoUsers
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories.RepoPosts
import com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories.RepoSynchronization
import com.ceiba.mobile.pruebadeingreso.R
import com.ceiba.mobile.pruebadeingreso.Utils.DialogueGeneric
import com.ceiba.mobile.pruebadeingreso.Utils.isNetworkAvailable
import com.ceiba.mobile.pruebadeingreso.Utils.showProgress

class HomeViewModel : ViewModel() {
    private val usersRepository = RepoUsers()
    private val postsRepository = RepoPosts()
    private val synchronization = RepoSynchronization()
    val user : MutableLiveData<Users> = MutableLiveData()
    var userProfile: MutableLiveData<MutableList<Users>> = usersRepository.getUsers()

    private fun getListUsers(){
        usersRepository.CallService()
        postsRepository.CallService()
    }

    fun setUser(user : Users){
        this.user.value = user
    }
    fun validateDB() {
        synchronization.getAllUsers(App.mContext!!).observeForever {
            if (it?.size != 0) {
                userProfile.value = (it as MutableList<Users>)
            } else {
                callService()
            }
        }
    }
    private fun callService(){
        if(isNetworkAvailable(App.mContext!!)) {
            getListUsers()
            App.mContext?.showProgress()
        } else {
            BaseActivity().dialogue(
                App.mContext?.resources?.getString(R.string.Internet)!!,
                App.mContext?.resources?.getString(R.string.detail_falla_Internet)!!,
                DialogueGeneric.TypeDialogue.ADVERTENCIA)
        }
    }
}
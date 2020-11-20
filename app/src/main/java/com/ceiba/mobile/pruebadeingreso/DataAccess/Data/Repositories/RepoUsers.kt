package com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories

import androidx.lifecycle.MutableLiveData
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.Base.BaseActivity
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Implementation.HandlerProxyRetrofitRx
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Resources.Services
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Users
import com.ceiba.mobile.pruebadeingreso.Utils.DialogueGeneric

class RepoUsers {

    private val synchronization = RepoSynchronization()
    private var userProfile : MutableLiveData<MutableList<Users>>?= getUsers()

    fun getUsers() : MutableLiveData<MutableList<Users>> {

        if (userProfile == null ){
            userProfile = MutableLiveData()
            userProfile?.value = emptyList<Users>().toMutableList()
        }
         return  userProfile!!
    }

    fun CallService (){
        HandlerProxyRetrofitRx(App.mContext!!)
                .withListenerAnswerListObjectcs {
                    userProfile?.value = it as MutableList<Users>
                    synchronization.onInsertUsers(App.mContext!!, it, ::onSuccessInsert)
                }
                .withListenerOfFailure { titulo, message ->
                    BaseActivity().dialogue(
                        titulo.toString(),
                        message,
                        DialogueGeneric.TypeDialogue.ERROR
                    )
                }
                .withMyClass(Users::class.java)
                .withMyService(Services.get_list_user)
                .withObjectSend(object : IRetrofitParcelable{})
                .RunService()
    }

    private fun onSuccessInsert(list: List<Long>) {
        val result: List<Long> = list
    }
}
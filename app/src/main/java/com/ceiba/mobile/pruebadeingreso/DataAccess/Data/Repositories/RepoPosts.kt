package com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories

import androidx.lifecycle.MutableLiveData
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.Base.BaseActivity
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Implementation.HandlerProxyRetrofitRx
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Resources.Services
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications
import com.ceiba.mobile.pruebadeingreso.Utils.DialogueGeneric

class RepoPosts {

    private val synchronization = RepoSynchronization()
    private var postsProfile : MutableLiveData<MutableList<Publications>>?= getPublications()

    fun getPublications() : MutableLiveData<MutableList<Publications>> {

        if (postsProfile == null ){
            postsProfile = MutableLiveData()
            postsProfile?.value = emptyList<Publications>().toMutableList()
        }
         return  postsProfile!!
    }

    fun CallService (){
        HandlerProxyRetrofitRx(App.mContext!!)
                .withListenerAnswerListObjectcs {
                    postsProfile?.value = it as MutableList<Publications>
                    synchronization.onInsertPublications(App.mContext!!, it, ::onSuccessInsert)
                }
                .withListenerOfFailure { titulo, message ->
                    BaseActivity().dialogue(
                        titulo.toString(),
                        message,
                        DialogueGeneric.TypeDialogue.ERROR
                    )
                }
                .withMyClass(Publications::class.java)
                .withMyService(Services.get_list_posts)
                .withObjectSend(object : IRetrofitParcelable{})
                .RunService()
    }

    private fun onSuccessInsert(list: List<Long>) {
        val result: List<Long> = list
    }
}
package com.ceiba.mobile.pruebadeingreso.DataAccess.Data.Repositories

import androidx.lifecycle.MutableLiveData
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Implementation.HandlerProxyRetrofitRx
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Resources.Services
import com.ceiba.mobile.pruebadeingreso.Models.MessageResponse
import com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB.Publications

class RepoPostID {

    private var postsIdProfile : MutableLiveData<MutableList<Publications>>?= getPublications()

    fun getPublications() : MutableLiveData<MutableList<Publications>> {

        if (postsIdProfile == null ){
            postsIdProfile = MutableLiveData()
            postsIdProfile?.value = emptyList<Publications>().toMutableList()
        }
         return  postsIdProfile!!
    }

    fun CallService (id: Int){
        HandlerProxyRetrofitRx(App.mContext!!)
                .withListenerAnswerListObjectcs {
                    postsIdProfile?.value = it as MutableList<Publications>
                }
                .withListenerOfFailure { titulo, message ->
                    val mess = MessageResponse()
                    mess.Code = titulo.toString()
                    mess.Message = message
                }
                .withMyClass(Publications::class.java)
                .withMyService(Services.get_list_posts_id.getUrlWithComplement(id.toString()))
                .withObjectSend(object : IRetrofitParcelable{})
                .RunService()
    }
}
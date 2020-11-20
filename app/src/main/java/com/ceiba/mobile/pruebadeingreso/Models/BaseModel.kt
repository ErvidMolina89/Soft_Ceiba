package com.ceiba.mobile.pruebadeingreso.Models

import com.ceiba.mobile.pruebadeingreso.DataAccess.Connection.Handler.Interfaces.IRetrofitParcelable
import com.google.gson.Gson
import java.io.Serializable

open class BaseModel :

    IRetrofitParcelable, Serializable {

    companion object {
        fun objectFromJson(json: String, type: Class<out BaseModel>): BaseModel? {
            try {
                val currentObject = Gson().fromJson(json, type)
                currentObject.doPostDeserializer()
                return currentObject
            } catch (e: com.google.gson.JsonSyntaxException) {
                return null
            }
        }
    }

    fun toJsonString(): String{
        return Gson().toJson(this)
    }

    open fun doPostDeserializer(){

    }
}
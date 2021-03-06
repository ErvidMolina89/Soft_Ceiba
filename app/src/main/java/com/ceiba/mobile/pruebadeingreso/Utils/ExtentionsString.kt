package com.ceiba.mobile.pruebadeingreso.Utils

import android.util.Log
import android.widget.Toast
import com.ceiba.mobile.pruebadeingreso.Base.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun String.convertToObject(clase : Class<*>) : Any?{
    return try {
        val objectString = Gson().fromJson(this,clase)
        objectString
    }catch (e: Exception){
        convertToListObject(clase)
    }
}

fun String.convertToListObject(clase : Class<*>) : MutableList<Any> ?{
    try {
        val gson = Gson()

        val type = object : TypeToken<MutableList<Any>>(){}.type
        val listRaw = gson.fromJson(this,type) as MutableList<*>
        val listaCasted = emptyList<Any>().toMutableList()

        for(objectStringRaw in listRaw){

            val objJson = gson.toJson(objectStringRaw)
            val objectStringCasted = gson.fromJson(objJson,clase)
            listaCasted.add(objectStringCasted)

        }

        return listaCasted
    }catch (e : Exception){
        Log.e("Error","",e)
        return null
    }
}

fun String.showInToast() : String{
    Toast.makeText(App.getInstance().applicationContext,this,Toast.LENGTH_LONG).show()
    return this
}

fun String.showInLogError(error: Throwable ?= null ) : String{
    Log.e("Error",this,error)
    return this
}
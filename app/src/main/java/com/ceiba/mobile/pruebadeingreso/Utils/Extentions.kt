@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.ceiba.mobile.pruebadeingreso.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ceiba.mobile.pruebadeingreso.Base.App
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * Created by exsis on 5/01/18.
 */

fun Context.showDialogueGenerico(){
    if(this !is AppCompatActivity){ return }
    DialogueGeneric.getInstance().showDialogueGenerico(supportFragmentManager,"DialogoGenerico")
}

fun isNetworkAvailable(context: Context): Boolean {
    try {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    } catch (e: Exception) {
        return false
    }
}

fun Context.showProgress(){
    if(this !is AppCompatActivity){
        return
    }else{
        runOnUiThread {
            ProgressBarPersonalized
                .getInstance()
                .show(supportFragmentManager,"progressbar")
        }

        GlobalScope.launch {
            delay(30_000)
            hiddenProgress()
        }
    }
}

fun Context.hiddenProgress(){
    if(this !is AppCompatActivity){
        return
    }else{
        runOnUiThread {
            ProgressBarPersonalized
                .getInstance()
                .dismiss()

        }
    }
}

fun String.showToast() : String{
    Toast.makeText(App.mContext!!.applicationContext,this, Toast.LENGTH_LONG).show()
    return this
}
package com.ceiba.mobile.pruebadeingreso.Base

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ceiba.mobile.pruebadeingreso.R
import com.ceiba.mobile.pruebadeingreso.Utils.DialogueGeneric
import com.ceiba.mobile.pruebadeingreso.Utils.showDialogueGenerico

@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.mContext = this
    }

    fun dialogue(title: String,
                 detail: String,
                 type: DialogueGeneric.TypeDialogue){
        DialogueGeneric
            .getInstance()
            .withTitle(title)
            .withText(detail)
            .withTypeDialogue(type)
            .withTextBtnAccept(R.string.btn_aceptar)
            .withActionBtnAccept {
                Log.e("","")
            }
        App.mContext?.showDialogueGenerico()
    }
}
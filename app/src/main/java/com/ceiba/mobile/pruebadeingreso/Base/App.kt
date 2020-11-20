package com.ceiba.mobile.pruebadeingreso.Base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

@SuppressLint("Registered")
class App: Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var mContext: Context?= null
        @SuppressLint("StaticFieldLeak")
        private var instance : App ?= null
        fun getInstance() : App{
            return instance!!
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}
package com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB

import com.ceiba.mobile.pruebadeingreso.Models.BaseModel
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Users(
    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "Name")      var name        : String? = null,
    @ColumnInfo(name = "UserName")  var username    : String? = null,
    @ColumnInfo(name = "Email")     var email       : String? = null,
    @ColumnInfo(name = "Phone")     var phone       : String? = null

): BaseModel()
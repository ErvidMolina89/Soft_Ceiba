package com.ceiba.mobile.pruebadeingreso.DataAccess.DBLocal.ModelsDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ceiba.mobile.pruebadeingreso.Models.BaseModel

@Entity(foreignKeys = [
    ForeignKey(entity = Users::class,
        parentColumns = ["Id"],
        childColumns = ["UserID"])
])
class Publications (

    @PrimaryKey
    @ColumnInfo(name = "Id")        var id          : Int? = null,
    @ColumnInfo(name = "UserID")    var userId      : Int? = null,
    @ColumnInfo(name = "Title")     var title       : String? = null,
    @ColumnInfo(name = "Body")      var body        : String? = null

):BaseModel()
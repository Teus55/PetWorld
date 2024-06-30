package com.petworld.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PetWorld(
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "user")
    var user: String?,
    @ColumnInfo(name = "shortPara")
    var shortPara: String?,
    @ColumnInfo(name = "url")
    var url: String?,
    @ColumnInfo(name = "category")
    var catergory: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
@Entity
data class DetailPetWorld(
    @ColumnInfo(name = "idPetworld")
    var idPetworld: String?,
    @ColumnInfo(name = "subTitle")
    var subtitle: String?,
    @ColumnInfo(name = "para")
    var para: String?
){
    @PrimaryKey(autoGenerate = true)
    var idDetail: Int = 0
}

@Entity
data class User(
    @ColumnInfo(name = "first")
    var first: String,
    @ColumnInfo(name = "last")
    var last: String,
    @ColumnInfo(name = "username")
    var username: String,
    @ColumnInfo(name = "password")
    var password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
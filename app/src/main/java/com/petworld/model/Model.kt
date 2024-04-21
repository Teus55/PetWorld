package com.petworld.model

import com.google.gson.annotations.SerializedName

data class PetWorld(
    var id: String?,
    var title: String?,
    var user: String?,
    @SerializedName("short_para")
    var shortPara: String?,
    var url: String?
)

data class DetailPetWorld(
    @SerializedName("id_berita")
    var idPetworld: String?,
    var id: String?,
    var subtitle: String?,
    @SerializedName("paragraf")
    var para: String?
)

data class User(
    var id: String?,
    @SerializedName("first_name")
    var first: String?,
    @SerializedName("last_name")
    var last: String?,
    var username: String?,
    var password: String?
)
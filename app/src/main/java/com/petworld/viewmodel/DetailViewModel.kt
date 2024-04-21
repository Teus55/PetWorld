package com.petworld.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.petworld.model.DetailPetWorld
import com.petworld.model.PetWorld

class DetailViewModel (application: Application) : AndroidViewModel(application){
    val detailPetWorldLD = MutableLiveData<ArrayList<DetailPetWorld>>()

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch() {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/uts_anmp/paragraf.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<DetailPetWorld>>() { }.type
                val result = Gson().fromJson<List<DetailPetWorld>>(it, sType)
                detailPetWorldLD.value = result as ArrayList<DetailPetWorld>?
                Log.d("showvoley", result.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}
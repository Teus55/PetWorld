package com.petworld.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.petworld.model.DetailPetWorld
import com.petworld.model.PetWorld
import com.petworld.model.PetWorldDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()

    fun addDetailPetWorld(list: List<DetailPetWorld>) {
        launch {
            val db = PetWorldDatabase.buildDatabase(
                getApplication()
            )
            db.petWorldDao().insertDetail(*list.toTypedArray())
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}


//class DetailViewModel (application: Application) : AndroidViewModel(application){
//    val detailPetWorldLD = MutableLiveData<ArrayList<DetailPetWorld>>()
//
//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null
//
//    fun fetch() {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/uts_anmp/paragraf.json"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<List<DetailPetWorld>>() { }.type
//                val result = Gson().fromJson<List<DetailPetWorld>>(it, sType)
//                detailPetWorldLD.value = result as ArrayList<DetailPetWorld>?
//                Log.d("showvoley", result.toString())
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }
//}
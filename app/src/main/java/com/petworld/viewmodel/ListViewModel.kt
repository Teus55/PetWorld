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
import com.petworld.model.PetWorld
import com.petworld.model.PetWorldDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListViewModel(application: Application)
    :AndroidViewModel(application), CoroutineScope {

    val petWorldLD = MutableLiveData<List<PetWorld>>()
    val petWorldLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val idPetLD = MutableLiveData<Int>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addPetWorld(list: List<PetWorld>) {
        launch {
            val db = PetWorldDatabase.buildDatabase(getApplication())
            val id = db.petWorldDao().insertPetWorld(*list.toTypedArray())

            if (id.isNotEmpty()) {
                idPetLD.postValue(id[0].toInt())
            }
            petWorldLD.postValue(db.petWorldDao().selectAllPetWorld())
        }
    }


    fun refresh() {
        loadingLD.value = true
        petWorldLoadErrorLD.value = false
        launch {
            val db = PetWorldDatabase.buildDatabase(
                getApplication()
            )

            petWorldLD.postValue(db.petWorldDao().selectAllPetWorld())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(petWorld: PetWorld) {
        launch {
            val db = PetWorldDatabase.buildDatabase(
                getApplication()
            )
            db.petWorldDao().deletePetWorld(petWorld)

            petWorldLD.postValue(db.petWorldDao().selectAllPetWorld())
        }
    }
}

package com.petworld.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.petworld.model.DetailPetWorld
import com.petworld.model.PetWorld
import com.petworld.model.PetWorldDatabase
import com.petworld.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()
    val db = buildDb(getApplication())
    val detailPetWorldLD = MutableLiveData<DetailPetWorld>()

    fun addDetailPetWorld(list: List<DetailPetWorld>) {
        launch {
            db.petWorldDao().insertDetail(*list.toTypedArray())
        }
    }

    fun fetch(id:Int) {
        launch {
            detailPetWorldLD.postValue(db.petWorldDao().selectDetail(id))
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}

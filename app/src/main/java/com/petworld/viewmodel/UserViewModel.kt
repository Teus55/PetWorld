package com.petworld.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.petworld.model.PetWorldDatabase
import com.petworld.model.User
import com.petworld.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val userLD = MutableLiveData<User?>()
    val db = buildDb(getApplication())
    private var job = Job()

    fun fetch(id:Int) {
        launch {
            userLD.postValue(db.petWorldDao().selectUser(id))
        }
    }

    fun register(list: List<User>) {
        launch {
            db.petWorldDao().registerUser(*list.toTypedArray())
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = db.petWorldDao().loginUser(username, password)
            userLD.postValue(user)
            user?.let {
                val sharedPreferences = getApplication<Application>().getSharedPreferences("user_prefs", 0)
                with(sharedPreferences.edit()) {
                    putInt("user_id", it.id)
                    apply()
                }
            }
        }
    }

    fun update(first: String, last: String, password: String, id: Int) {
        launch {
            db.petWorldDao().update(first, last, password, id)
            //Log.d("cekvar", idPetLD.toString())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}

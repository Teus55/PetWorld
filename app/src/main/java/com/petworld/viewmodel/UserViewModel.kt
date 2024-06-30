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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class UserViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    val userLD = MutableLiveData<User?>()
    private var job = Job()


    fun register(list: List<User>) {
        launch {
            val db = PetWorldDatabase.buildDatabase(
                getApplication()
            )
            db.petWorldDao().registerUser(*list.toTypedArray())
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val db = PetWorldDatabase.buildDatabase(
                getApplication() )
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

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}


//    val TAG = "volleyTag"
//    private var queue: RequestQueue? = null

//    fun fetch() {
//        queue = Volley.newRequestQueue(getApplication())
//        val url = "http://10.0.2.2/uts_anmp/users.php"
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object : TypeToken<List<User>>() { }.type
//                val result = Gson().fromJson<List<User>>(it, sType)
//                userLD.value = result as ArrayList<User>?
//                Log.d("showvoley", result.toString())
//            },
//            {
//                Log.d("showvoley", it.toString())
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

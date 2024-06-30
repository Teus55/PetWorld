package com.petworld.view

import android.view.View
import com.petworld.model.User

interface UserSaveChangesClick {
    fun onSaveChangesClick(v: View, obj: User)
}
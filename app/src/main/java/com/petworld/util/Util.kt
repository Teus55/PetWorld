package com.petworld.util

import android.content.Context
import com.petworld.model.PetWorldDatabase

val DB_NAME = "newpetworlddb"


fun buildDb(context: Context): PetWorldDatabase {
    val db = PetWorldDatabase.buildDatabase(context)
    return db
}

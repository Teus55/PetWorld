package com.petworld.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.petworld.model.PetWorldDatabase

val DB_NAME = "newpetworlddb"


fun buildDb(context: Context): PetWorldDatabase {
    val db = PetWorldDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE PetWorld ADD COLUMN category VARCHAR(100)")
    }
}

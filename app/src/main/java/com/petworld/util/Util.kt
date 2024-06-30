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

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE user_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                first TEXT NOT NULL DEFAULT '', 
                last TEXT NOT NULL DEFAULT '', 
                username TEXT NOT NULL DEFAULT '', 
                password TEXT NOT NULL DEFAULT ''
            )
        """)

        database.execSQL("""
            INSERT INTO user_new (id, first, last, username, password) 
            SELECT id, 
                   IFNULL(first, ''), 
                   IFNULL(last, ''), 
                   IFNULL(username, ''), 
                   IFNULL(password, '') 
            FROM user
        """)

        database.execSQL("DROP TABLE user")
        database.execSQL("ALTER TABLE user_new RENAME TO user")
    }
}
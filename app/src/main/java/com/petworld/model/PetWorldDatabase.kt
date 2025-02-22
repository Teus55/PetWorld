package com.petworld.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.petworld.util.DB_NAME
import com.petworld.util.MIGRATION_1_2
import com.petworld.util.MIGRATION_2_3

@Database(entities =[PetWorld::class, DetailPetWorld::class, User::class], version =  3)
abstract class PetWorldDatabase:RoomDatabase() {
    abstract fun petWorldDao(): PetWorldDao

    companion object{
        @Volatile
        private var instance: PetWorldDatabase ?= null
        private val LOCK = Any()

//        fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                PetWorldDatabase::class.java,
//                DB_NAME).build()

        fun buildDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext,
            PetWorldDatabase::class.java, DB_NAME)
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }
}
package com.petworld.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PetWorldDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPetWorld(vararg petWorld:PetWorld) : List<Long>
    @Query("SELECT * FROM petWorld ORDER BY id desc")
    fun selectAllPetWorld(): List<PetWorld>
    @Query("SELECT * FROM petWorld WHERE id= :id")
    fun selectPetWorld(id:Int): PetWorld
    @Delete
    fun deletePetWorld(petWorld: PetWorld)

    //detail
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetail(vararg detailPetWorld:DetailPetWorld)
    @Query("SELECT * FROM detailPetWorld WHERE idPetworld= :id")
    fun selectDetail(id:Int) : DetailPetWorld
    @Delete
    fun deleteDetail(detailPetWorld: DetailPetWorld)


    //user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(vararg user:User)
    @Query("SELECT * FROM user WHERE id= :id")
    fun selectUser(id:Int) : User
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun loginUser(username:String, password:String): User?
    @Query("UPDATE user SET first=:first, last=:last, password=:password WHERE id = :id")
    fun update(first: String, last: String, password: String, id: Int)
    @Delete
    fun deleteUser(user: User)
}
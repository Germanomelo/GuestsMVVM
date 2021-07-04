package com.gdev.guestsmvvm.service.repository.dao

import androidx.room.*
import com.gdev.guestsmvvm.service.model.GuestModel

@Dao
interface GuestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Query("DELETE FROM guest WHERE id = :id")
    fun delete(id: Int): Int

    @Query("SELECT * FROM guest WHERE id = :id")
    fun findById(id: Int): GuestModel

    @Query("SELECT * FROM guest")
    fun getAll():List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = 1")
    fun getPresent():List<GuestModel>

    @Query("SELECT * FROM guest WHERE presence = 0")
    fun getAbsent():List<GuestModel>

}
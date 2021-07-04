package com.gdev.guestsmvvm.service.repository

import android.content.Context
import com.gdev.guestsmvvm.service.model.GuestModel

class GuestRepository constructor(context: Context){

   private val mAppDatabase = AppDatabase.getDatabase(context).guestDao()

    fun getAll():List<GuestModel>{
        return mAppDatabase.getAll()
    }

    fun getPresent():List<GuestModel>{
        return mAppDatabase.getPresent()
    }

    fun getAbsent():List<GuestModel>{
        return mAppDatabase.getAbsent()
    }

    fun get(id: Int): GuestModel {
        return mAppDatabase.findById(id)
    }

    fun create(guest: GuestModel): Boolean{
        return mAppDatabase.create(guest) > 0
    }

    fun update(guest: GuestModel):Boolean{
        return mAppDatabase.update(guest) > 0
    }

    fun delete(id: Int): Boolean{
        return  mAppDatabase.delete(id) > 0
    }
}
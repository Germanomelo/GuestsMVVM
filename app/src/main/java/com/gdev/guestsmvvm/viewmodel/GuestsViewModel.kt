package com.gdev.guestsmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gdev.guestsmvvm.service.constants.AppConstants
import com.gdev.guestsmvvm.service.model.GuestModel
import com.gdev.guestsmvvm.service.repository.GuestRepository

class GuestsViewModel(private val guestRepository: GuestRepository) : ViewModel() {

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun listGuests(filter: Int){
         when(filter){
            AppConstants.FILTER_ALL -> mGuestList.value = guestRepository.getAll()
            AppConstants.FILTER_PRESENTS -> mGuestList.value = guestRepository.getPresent()
            AppConstants.FILTER_ABSENTS -> mGuestList.value = guestRepository.getAbsent()
        }
    }

    fun delete(id: Int) {
       guestRepository.delete(id)
    }
}
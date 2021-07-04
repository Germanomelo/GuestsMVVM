package com.gdev.guestsmvvm.viewmodel

import androidx.lifecycle.*
import com.gdev.guestsmvvm.service.repository.GuestRepository
import com.gdev.guestsmvvm.service.model.GuestModel

class GuestFormViewModel(private val guestRepository: GuestRepository) : ViewModel() {

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest;

    private var mLoadGuest = MutableLiveData<GuestModel>()
    val loadGuest: LiveData<GuestModel> = mLoadGuest;

    fun saveGuest(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id, name, presence)
        if (id == 0) {
            mSaveGuest.value = guestRepository.create(guest)
        } else {
            mSaveGuest.value = guestRepository.update(guest)
        }

    }

    fun loadGuest(id: Int) {
        mLoadGuest.value = guestRepository.get(id)
    }
}
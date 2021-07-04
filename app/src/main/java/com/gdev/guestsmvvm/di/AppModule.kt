package com.gdev.guestsmvvm.di

import com.gdev.guestsmvvm.service.repository.GuestRepository
import com.gdev.guestsmvvm.viewmodel.GuestFormViewModel
import com.gdev.guestsmvvm.viewmodel.GuestsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModuleModule = module {
    viewModel{(guestRep: GuestRepository) -> GuestFormViewModel(guestRep)}
    viewModel{(guestRep: GuestRepository) -> GuestsViewModel(guestRep) }
}

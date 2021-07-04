package com.gdev.guestsmvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdev.guestsmvvm.databinding.FragmentAllGuestsBinding
import com.gdev.guestsmvvm.service.constants.AppConstants
import com.gdev.guestsmvvm.service.repository.GuestRepository
import com.gdev.guestsmvvm.view.adapter.GuestAdapter
import com.gdev.guestsmvvm.view.listener.GuestListener
import com.gdev.guestsmvvm.viewmodel.GuestsViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AllGuestsFragment : Fragment() {

    private val mViewModel: GuestsViewModel by viewModel{
        parametersOf(activity?.let { GuestRepository(it.applicationContext) })
    }
    private lateinit var mBinding: FragmentAllGuestsBinding
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener
    private var mFilter = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAllGuestsBinding.inflate(layoutInflater, container, false)
        main()
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        // Obtendo filtro
        val bundle = arguments
        if (bundle != null) {
            this.mFilter = bundle.getInt(AppConstants.GUEST_FILTER_KEY)
        }

        mViewModel.listGuests(this.mFilter)
    }

    private fun main(){
        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()

                bundle.putInt(AppConstants.GUEST_ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.listGuests(mFilter)
            }
        }

        observer()
        createRecycler()
    }

    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.refreshList(it)
        })
    }

    private fun createRecycler(){
        mBinding.recyclerGuests.layoutManager = LinearLayoutManager(context)
        mBinding.recyclerGuests.adapter = mAdapter
        mAdapter.attachListener(mListener)
    }
}
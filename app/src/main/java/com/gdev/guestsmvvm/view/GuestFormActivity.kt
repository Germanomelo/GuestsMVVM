package com.gdev.guestsmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.gdev.guestsmvvm.databinding.ActivityGuestFormBinding
import com.gdev.guestsmvvm.service.constants.AppConstants
import com.gdev.guestsmvvm.service.repository.GuestRepository
import com.gdev.guestsmvvm.viewmodel.GuestFormViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GuestFormActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityGuestFormBinding

    private val mViewModel: GuestFormViewModel by viewModel{
        parametersOf(GuestRepository(applicationContext))
    }

    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.radioPresent.isChecked = true

        main()
    }

    private fun main() {
        setListeners()
        observer()
        loadData()
    }

    private fun setListeners() {
        mBinding.buttonSave.setOnClickListener(View.OnClickListener {
            mViewModel.saveGuest(
                mGuestId,
                mBinding.editName.text.toString(),
                mBinding.radioPresent.isChecked
            )
        })
    }

    private fun observer() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Falha!", Toast.LENGTH_LONG).show()
            }
            finish()
        })

        mViewModel.loadGuest.observe(this, Observer {
            mBinding.editName.setText(it.name)
            if (!it.presence) {
                mBinding.radioAbsent.isChecked = true
            }
            mBinding.buttonSave.text = "Atualizar"
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(AppConstants.GUEST_ID)
            mViewModel.loadGuest(mGuestId)
        }
    }


}
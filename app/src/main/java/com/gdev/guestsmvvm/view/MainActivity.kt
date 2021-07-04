package com.gdev.guestsmvvm.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.gdev.guestsmvvm.R
import com.gdev.guestsmvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_all, R.id.nav_presents, R.id.nav_absents
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        mBinding.bottomNavigationView.setupWithNavController(navController)

        handleEvent()
    }

    private fun handleEvent() {
        mBinding.fab.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, GuestFormActivity::class.java))
        })
    }
}
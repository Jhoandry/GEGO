package com.lugloc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.lugloc.R
import com.lugloc.R.style.AppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Navigation.findNavController(this, R.id.main_start_fragment).navigate(R.id.startupFragment)
    }
}
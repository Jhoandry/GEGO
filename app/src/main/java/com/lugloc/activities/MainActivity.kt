package com.lugloc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.lugloc.R
import com.lugloc.R.style.AppTheme
import com.lugloc.utils.config.KoinApplicationGlobalContext
import com.lugloc.utils.config.networkingModule
import com.lugloc.utils.config.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (KoinApplicationGlobalContext.getOrNull() == null) {
            KoinApplicationGlobalContext.app = startKoin {
                androidContext(this@MainActivity)
                modules(
                        listOf(useCasesModule, networkingModule))
            }
        }

        Navigation.findNavController(this, R.id.main_start_fragment).navigate(R.id.startupFragment)
    }
}
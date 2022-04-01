package com.lugloc.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lugloc.R
import com.lugloc.activities.adapters.StartupAdapter
import kotlinx.android.synthetic.main.fragment_startup.*
import androidx.navigation.fragment.findNavController


class StartupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_startup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setClickListeners()
    }

    private fun setAdapter() {
        vw_startup_background.adapter = StartupAdapter(this.requireContext())
        vw_startup_background.offscreenPageLimit = 3 //TODO - add like property
        cv_startup_step.setViewPager(vw_startup_background)
    }

    private fun setClickListeners() {
        btn_signup_action?.setOnClickListener {
            findNavController().navigate(R.id.action_startupFragment_to_signup_navigation)
        }

        tv_go_login?.setOnClickListener {
            findNavController().navigate(R.id.action_startupFragment_to_loginFragment)
        }
    }
}
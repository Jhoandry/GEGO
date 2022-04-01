package com.lugloc.activities.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lugloc.R
import kotlinx.android.synthetic.main.fragment_name_step.*
import kotlinx.android.synthetic.main.fragment_password_step.*

class PasswordStepFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        btn_go_done?.setOnClickListener {
            findNavController().navigate(R.id.action_passwordStepFragment_to_register_device_navigation)
        }
    }
}
package com.project.alerzointerview.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.alerzointerview.R
import com.project.alerzointerview.util.SessionManager
import com.project.alerzointerview.util.Validation
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_signup.*


class SignInFragment : Fragment() {
    var isPasswordVisible:Boolean = false
    var sessionManager:SessionManager?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionManager= SessionManager(requireContext())

        handleClicks()
    }

    private fun handleClicks(){
        btnLogin.setOnClickListener {
            val validation = Validation()
            if(validation.verifyEmailIsValid(et_signin_emailaddress.text.toString()) &&
                validation.verifyPasswordIsValid(et_password.text.toString()) &&
                sessionManager?.getEmail()==et_signin_emailaddress.text.toString() &&
                sessionManager?.getPassword()==et_password.text.toString()) {

                findNavController().navigate(R.id.action_signInFragment_to_postsFragment)
            }

            else if(!validation.verifyEmailIsValid(et_signin_emailaddress.text.toString())){
                Toast.makeText(requireContext(),"Email Address is invalid", Toast.LENGTH_SHORT).show()
            }

            else if(!validation.verifyPasswordIsValid(et_password.text.toString())){
                Toast.makeText(requireContext(),"Password should be provided and must be greater than 6 characters",
                    Toast.LENGTH_SHORT).show()
            }

            else{
                Toast.makeText(requireContext(),"You are not signed up please sign up",Toast.LENGTH_SHORT).show()
            }
        }

        tvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signupFragment)
        }

        et_password.setOnTouchListener(onTouchListener)
    }

    private val onTouchListener = View.OnTouchListener { v, event ->
        v.performClick()
        val RIGHT = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= et_password.getRight() - et_password.getCompoundDrawables()
                    .get(RIGHT)
                    .getBounds().width()
            ) {
                val selection: Int = et_password.getSelectionEnd()
                if (isPasswordVisible) {
                    // set drawable image
                    et_password.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_password_toggle_off,
                        0
                    )
                    // hide Password
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance())
                    isPasswordVisible = false
                } else {
                    // set drawable image
                    et_password.setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_password_toggle,
                        0
                    )
                    // show Password
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                    isPasswordVisible = true
                }
                et_password.setSelection(selection)
                return@OnTouchListener true
            }
        }
        false
    }



}
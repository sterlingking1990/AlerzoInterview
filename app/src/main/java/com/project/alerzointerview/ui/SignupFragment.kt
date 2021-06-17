package com.project.alerzointerview.ui

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.project.alerzointerview.R
import com.project.alerzointerview.util.SessionManager
import com.project.alerzointerview.util.Validation
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {

    var isPasswordVisible:Boolean = false
    var sessionManager:SessionManager?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sessionManager= SessionManager(requireContext())

        handleClicks()


        }

    private fun handleClicks(){
        etPasswordSigUp.setOnTouchListener(onTouchListener)

        btnSignUp.setOnClickListener {
            val validation = Validation()
            if(validation.verifyNameIsValid(et_signup_firstname.text.toString()) &&
                    validation.verifyNameIsValid(et_lastname.text.toString()) &&
                    validation.verifyEmailIsValid(et_signup_emailaddress.text.toString()) &&
                    validation.verifyPasswordIsValid(etPasswordSigUp.text.toString())) {

                sessionManager?.saveEmail(et_signup_emailaddress.text.toString())
                sessionManager?.savePassword(etPasswordSigUp.text.toString())
                sessionManager?.saveFirstName(et_signup_firstname.text.toString())

                findNavController().navigate(R.id.action_signupFragment_to_signInFragment)
            }

            else if(!validation.verifyNameIsValid(et_signup_firstname.text.toString()) || !validation.verifyNameIsValid(et_lastname.text.toString())){
                Toast.makeText(requireContext(),"First name and last name should be provided",Toast.LENGTH_SHORT).show()
            }

            else if(!validation.verifyEmailIsValid(et_signup_emailaddress.text.toString())){
                Toast.makeText(requireContext(),"Email Address is invalid",Toast.LENGTH_SHORT).show()
            }

            else if(!validation.verifyPasswordIsValid(etPasswordSigUp.text.toString())){
                Toast.makeText(requireContext(),"Password should be provided and must be greater than 6 characters",Toast.LENGTH_SHORT).show()
            }

        }

        tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_signInFragment)
        }
    }


    private val onTouchListener = View.OnTouchListener { v, event ->
        v.performClick()
        val RIGHT = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= etPasswordSigUp.getRight() - etPasswordSigUp.getCompoundDrawables()
                            .get(RIGHT)
                            .getBounds().width()
            ) {
                val selection: Int = etPasswordSigUp.getSelectionEnd()
                if (isPasswordVisible) {
                    // set drawable image
                    etPasswordSigUp.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_password_toggle_off,
                            0
                    )
                    // hide Password
                    etPasswordSigUp.setTransformationMethod(PasswordTransformationMethod.getInstance())
                    isPasswordVisible = false
                } else {
                    // set drawable image
                    etPasswordSigUp.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_password_toggle,
                            0
                    )
                    // show Password
                    etPasswordSigUp.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                    isPasswordVisible = true
                }
                etPasswordSigUp.setSelection(selection)
                return@OnTouchListener true
            }
        }
        false
    }

}
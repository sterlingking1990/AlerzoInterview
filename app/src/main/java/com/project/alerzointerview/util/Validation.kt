package com.project.alerzointerview.util

import android.util.Patterns
import androidx.core.text.isDigitsOnly

class Validation {

    fun verifyNameIsValid(nameInput:String):Boolean{
        return !nameInput.isEmpty()
    }

    fun verifyEmailIsValid(emailInput:String):Boolean{
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

        return if (emailInput.isEmpty()) {
            false
        } else EMAIL_REGEX.toRegex().matches(emailInput);
    }

    fun verifyPasswordIsValid(passwordInput:String):Boolean{
        if (passwordInput.isEmpty()) {
            return false
        } else return passwordInput.length >= 6

    }
}
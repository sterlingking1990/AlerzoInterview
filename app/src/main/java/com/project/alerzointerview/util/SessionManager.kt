package com.project.alerzointerview.util

import android.content.Context
import android.content.SharedPreferences
import com.project.alerzointerview.R

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val EMAIL="email"
        const val PASSWORD="password"
        const val FIRSTNAME="firstname"

    }

    fun saveEmail(email: String) {
        val editor = prefs.edit()
        editor.putString(EMAIL, email)
        editor.apply()
    }

    fun saveFirstName(firstName: String) {
        val editor = prefs.edit()
        editor.putString(FIRSTNAME, firstName)
        editor.apply()
    }

    fun getFirstName():String?{
        return prefs.getString(FIRSTNAME,null)
    }

    fun getEmail():String? {
        return prefs.getString(EMAIL,null)
    }

    fun savePassword(passsword:String){
        val editor = prefs.edit()
        editor.putString(PASSWORD, passsword)
        editor.apply()
    }

    fun getPassword():String? {
        return prefs.getString(PASSWORD,null)
    }

    fun clearData(){
        prefs.all.clear()
    }

}
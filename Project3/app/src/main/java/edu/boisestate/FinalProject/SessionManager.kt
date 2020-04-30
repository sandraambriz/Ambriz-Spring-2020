package edu.boisestate.FinalProject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

class SessionManager {

    lateinit var pref:SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var context: Context

    val PRIVATE_MODE:Int = 0

    constructor(context: Context){
        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object{
        val PREF_NAME = "pref"
        val IS_LOGIN = "isLoggedIn"
        val KEY_NAME = "name"
        val KEY_EMAIL  = "email"
    }

    fun loginSessionStart(name:String, email:String){
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.commit()
    }

    fun checkLogin(){
        if(!this.isLoggedIn()){
            val i:Intent = Intent(context, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }

    fun isLoggedIn():Boolean{
       return pref.getBoolean(IS_LOGIN, false)
    }

    fun logout(){
        editor.clear()
        editor.commit()

        val i:Intent = Intent(context, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
    }

    fun getUserDetails():HashMap<String?, String?> {
        val user:HashMap<String?, String?> = HashMap<String?, String?>()
        user.put(KEY_NAME, pref.getString(KEY_NAME, null))
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null))
        return user
    }
}
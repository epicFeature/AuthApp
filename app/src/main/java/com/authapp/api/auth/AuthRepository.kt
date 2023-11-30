package com.authapp.api.auth

import android.content.Context
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "preference_name"
private const val TOKEN = "token"

class AuthRepository (context:Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )
    private var editor = prefs.edit()

    fun saveToken(text: String) {
        editor.putString(TOKEN, text).apply()
    }

    fun getToken() = prefs.getString(TOKEN, null)

    //проверить верно ли
    fun deleteToken()=prefs.edit().clear().apply()
}
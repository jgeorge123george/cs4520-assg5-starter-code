package com.cs4520.assgn5.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cs4520.assgn5.logic.Auth

class LoginViewModel() : ViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess
    private val auth = Auth()
    fun fetchDta(username: String, password: String) {
        val success = auth.login(username, password)
        _loginSuccess.value = success
    }
}
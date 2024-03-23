package com.cs4520.assgn5.logic


class Auth {
    fun login(username: String, password: String): Boolean {
        return username == "admin" && password == "admin"
    }
}

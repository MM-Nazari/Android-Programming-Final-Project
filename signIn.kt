package com.example.signup

import java.security.MessageDigest

class signIn {

    fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(password.toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }


    fun login(username: String, password: String, userDao: UserDao): String {
        val user = userDao.findUserByUsername(username)
        if (user != null) {
            val inputPasswordHash = hashPassword(password)
            if (user.password == inputPasswordHash) {
                return "Sign in successful"
            }
        }
        return "Wrong username or password"
    }

}

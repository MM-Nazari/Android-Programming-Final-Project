package com.example.signup

import java.security.MessageDigest
import com.example.signup.UserDao
import com.example.signup.User

class signUp() {
    fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(password.toByteArray())
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

    fun registerUser(
        name: String,
        lastname: String,
        username: String,
        password: String,
        userDao: UserDao
    ) {
        val existingUser = userDao.findUserByUsername(username)
        if (existingUser != null) {
            println("Username already exists.")
            return
        }

        val hashedPassword = hashPassword(password)
        val newUser =
            User(name = name, lastname = lastname, username = username, password = hashedPassword)
        userDao.insertUser(newUser)
        println("User registered successfully.")
    }
}
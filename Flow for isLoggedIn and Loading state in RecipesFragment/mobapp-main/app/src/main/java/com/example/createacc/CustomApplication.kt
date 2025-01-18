package com.example.createacc

import android.app.Application

class CustomApplication : Application() {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreate() {
        super.onCreate()
        credentialsManager = CredentialsManager(this)
    }

    fun getCredentialsManager(): CredentialsManager {
        return credentialsManager
    }
}

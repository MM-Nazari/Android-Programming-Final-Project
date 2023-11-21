package com.example.project_androidcourse

import android.content.Context

class ContextHandler private constructor() {
    companion object {
        @Volatile
        private var instance: Context? = null
        fun get() = instance ?: synchronized(this) {
            null
        }
        fun set(ctx: Context) {
            instance = ctx
        }
    }

}
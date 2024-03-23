package com.cs4520.assgn5.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun DBProductDao(): DBProductDao
}


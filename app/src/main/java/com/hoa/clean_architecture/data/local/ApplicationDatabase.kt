package com.hoa.clean_architecture.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        MenuItemEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun menuItemDao(): MenuItemDao

    companion object {

        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        "application_database.db"
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
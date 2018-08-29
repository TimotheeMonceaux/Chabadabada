package timothee.chabadabada.core

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import timothee.chabadabada.model.Word
import timothee.chabadabada.model.dao.WordDao

@Database(entities = arrayOf(Word::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    // TODO go back to real database
//                    INSTANCE = Room.databaseBuilder(context.applicationContext,
//                                                    AppDatabase::class.java,
//                                                    "chabadabada.db")
//                                   .build()
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext,
                                                    AppDatabase::class.java)
                                   .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
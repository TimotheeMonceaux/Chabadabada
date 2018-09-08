package timothee.chabadabada.core

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import timothee.chabadabada.R
import timothee.chabadabada.model.Word
import timothee.chabadabada.model.dao.WordDao

@Database(entities = arrayOf(Word::class), version = 1)
abstract class DatabaseAccessManager : RoomDatabase() {

    abstract fun wordDao(): WordDao

    fun initialize(context: Context) {
        clearDatabase()
        wordDao()?.insert(Word.listDeserialize(context.resources.openRawResource(R.raw.word).bufferedReader().use {it.readText()}))
    }

    private fun clearDatabase(): Boolean {
        wordDao()?.deleteAll()
        return true
    }

    companion object {

        private var INSTANCE: DatabaseAccessManager? = null

        fun getInstance(context: Context): DatabaseAccessManager? {
            if (INSTANCE == null) {
                synchronized(DatabaseAccessManager::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                                    DatabaseAccessManager::class.java,
                                                    context.getString(R.string.db_name))
                                   .allowMainThreadQueries()
                                   .build()
                    INSTANCE?.initialize(context)
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
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

    var values: List<Word> = listOf()
    var count = 0

    fun initialize() {
        values = wordDao().getAll().shuffled()
        count = 0
    }

    fun getValue(): String {
        return values[count++].word
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
                    INSTANCE?.wordDao()?.insert(Word.listDeserialize(context.resources.openRawResource(R.raw.word).bufferedReader().use {it.readText()}))
                    INSTANCE?.initialize()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
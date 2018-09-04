package timothee.chabadabada.core

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import timothee.chabadabada.R
import timothee.chabadabada.model.Word
import timothee.chabadabada.model.dao.WordDao

@Database(entities = arrayOf(Word::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    var values: List<Word> = listOf()
    var count = 0

    fun initialize() {
        values = wordDao()?.getAll().shuffled()
        count = 0
    }

    fun getValue(): String {
        return values[count++].word
    }

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                                                    AppDatabase::class.java,
                                                    context.getString(R.string.db_name))
                                   .allowMainThreadQueries()
                                   .build()

                    INSTANCE?.wordDao()?.insert(defaultValues)
                    INSTANCE?.initialize()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private val defaultValues: List<Word> = listOf(
                Word("FAUT"),
                Word("AMOUR"),
                Word("TEMPS"),
                Word("SEXE"),
                Word("VOLE"),
                Word("SAIS"),
                Word("FAIRE"),
                Word("DANSE"),
                Word("PAPA"),
                Word("DIRE"),
                Word("COEUR"),
                Word("TOMBER"),
                Word("BEAU"),
                Word("POURQUOI"),
                Word("NUIT"),
                Word("VEUX"),
                Word("AIME"),
                Word("JOUR"),
                Word("DANSER"),
                Word("LAISSE"),
                Word("MOTS"),
                Word("MONDE"),
                Word("SOIR"),
                Word("VIENS"),
                Word("PEUX"),
                Word("LOUP"),
                Word("PEUR"),
                Word("CIEL"),
                Word("VENT"),
                Word("LAID"),
                Word("DINGUE"),
                Word("VAIS"),
                Word("BRAS"),
                Word("FOND"),
                Word("SILENCE"),
                Word("AIMER"),
                Word("GENS"),
                Word("CROIS"),
                Word("HAUT"),
                Word("APPELLE"),
                Word("VOIR"),
                Word("ENTENDS"),
                Word("GRAND"),
                Word("TOP")
        )
    }
}
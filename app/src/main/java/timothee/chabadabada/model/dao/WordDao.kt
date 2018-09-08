package timothee.chabadabada.model.dao

import android.arch.persistence.room.*
import timothee.chabadabada.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words LIMIT 1")
    fun first(): Word

    @Query("SELECT * FROM words")
    fun getAll(): List<Word>

    @Query("SELECT COUNT(*) FROM words")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(words: List<Word>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg words: Word)

    @Update
    fun update(word: Word)

    @Query("DELETE FROM words")
    fun deleteAll()
}
package timothee.chabadabada.core

import android.content.Context
import timothee.chabadabada.model.Word
import kotlin.coroutines.experimental.buildIterator

class WordStreamer(val context: Context) {

    val wordIterator: Iterator<Word> = getIterator()

    private fun getIterator(): Iterator<Word> = buildIterator{
        while (true) {
            yieldAll(DatabaseAccessManager.getInstance(context)?.wordDao()?.getAll()?.shuffled()?.iterator()!!)
        }
    }

    fun nextValue(): Word {
        return wordIterator.next()
    }
}
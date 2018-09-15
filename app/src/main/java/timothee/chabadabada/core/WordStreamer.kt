package timothee.chabadabada.core

import android.content.Context
import timothee.chabadabada.model.Word
import timothee.chabadabada.model.raw.Language
import java.security.InvalidParameterException
import kotlin.coroutines.experimental.buildIterator

class WordStreamer private constructor(val context: Context, val language: Language) {

    val wordIterator: Iterator<Word> = getIterator()

    private fun getIterator(): Iterator<Word> = buildIterator{
        while (true) {
            yieldAll(DatabaseAccessManager.getInstance(context)?.wordDao()?.getAll()?.shuffled()?.iterator()!!)
        }
    }

    fun nextValue(): Word {
        return wordIterator.next()
    }

    companion object {
        val INSTANCES: MutableMap<Language, WordStreamer?> = mutableMapOf(Language.French to null, Language.English to null)

        fun getInstance(context: Context, language: Language): WordStreamer? {
            // Sanity check on the parameters
            if (!INSTANCES.containsKey(language))
                throw InvalidParameterException("Trying to access to an non-existing WordStreamer: $language")

            if (INSTANCES[language] == null)
                INSTANCES[language] = WordStreamer(context, language)
            return INSTANCES[language]
        }

        fun destroyInstance(language: Language) {
            // Sanity check on the parameters
            if (!INSTANCES.containsKey(language))
                throw InvalidParameterException("Trying to access to an non-existing WordStreamer: $language")
            INSTANCES[language] = null
        }
    }
}
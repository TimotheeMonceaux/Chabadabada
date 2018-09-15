package timothee.chabadabada

import org.junit.Test

import org.junit.Assert.*
import timothee.chabadabada.model.Word
import timothee.chabadabada.model.raw.Language

class ModelUnitTest {
    @Test
    fun word_word_serialization_isCorrect() {
        var word = Word("WORD")
        assertEquals(word.word, Word.deserialize(word.serialize()).word)
    }

    @Test
    fun word_language_serialization_isCorrect() {
        var word = Word("WORD", "French")
        assertEquals(word.language, Word.deserialize(word.serialize()).language)
        assertEquals(Language.valueOf(word.language), Language.French)
    }
}

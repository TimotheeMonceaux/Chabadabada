package timothee.chabadabada

import org.junit.Test

import org.junit.Assert.*
import timothee.chabadabada.model.Word

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class ModelUnitTest {
    @Test
    fun serialization_isCorrect() {
        var word = Word("WORD")
        assertEquals(word.word, Word.deserialize(word.serialize()).word)
    }
}

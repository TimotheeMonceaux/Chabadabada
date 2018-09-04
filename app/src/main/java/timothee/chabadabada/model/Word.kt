package timothee.chabadabada.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import timothee.chabadabada.model.interfaces.IDeserializable
import timothee.chabadabada.model.interfaces.ISerializable

@Entity(tableName = "words")
class Word(val word: String) : ISerializable {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override fun serialize(): String {
        return Gson().toJson(this)
    }

    companion object : IDeserializable {

        override fun deserialize(json: String): Word {
            return Gson().fromJson(json, Word::class.java)
        }

        override fun listDeserialize(json: String): List<ISerializable> {
            return Gson().fromJson(json, listOf<Word>()::class.java)
        }
    }
}
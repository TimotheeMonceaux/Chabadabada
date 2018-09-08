package timothee.chabadabada.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

        inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

        override fun listDeserialize(json: String): MutableList<Word> {
            return Gson().fromJson(json)
        }
    }
}
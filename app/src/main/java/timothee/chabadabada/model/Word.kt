package timothee.chabadabada.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import timothee.chabadabada.model.interfaces.IDeserializable
import timothee.chabadabada.model.interfaces.ISerializable

@Entity(tableName = "words")
//class Word(val word: String) : ISerializable {
class Word(val word: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

//    override fun serialize(): String {
//        return word
//    }
//
//    companion object : IDeserializable {
//
//        override fun deserialize(json: String): ISerializable {
//            return Word(json)
//        }
//    }
}
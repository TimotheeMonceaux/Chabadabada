package timothee.chabadabada.model

import timothee.chabadabada.model.interfaces.IDeserializable
import timothee.chabadabada.model.interfaces.ISerializable

class Word(val word: String) : ISerializable {

    override fun serialize(): String {
        return word
    }

    companion object : IDeserializable {

        override fun deserialize(json: String): ISerializable {
            return Word(json)
        }
    }
}
package timothee.chabadabada.model.interfaces

interface ISerializable {
    fun serialize(): String
}

interface IDeserializable {
    fun deserialize(json: String): ISerializable
}
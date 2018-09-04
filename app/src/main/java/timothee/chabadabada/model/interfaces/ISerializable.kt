package timothee.chabadabada.model.interfaces

interface ISerializable {
    fun serialize(): String
}

interface IDeserializable {
    fun deserialize(json: String): ISerializable
    fun listDeserialize(json: String): List<ISerializable>
}
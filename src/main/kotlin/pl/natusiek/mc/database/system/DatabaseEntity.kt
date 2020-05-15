package pl.natusiek.mc.database.system

import pl.natusiek.mc.database.DatabaseAPI
import java.io.Serializable

abstract class DatabaseEntity : Serializable {

    abstract val id: String
    abstract val key: String
    abstract val collection: String

    fun insertEntity() =
        DatabaseAPI.database.insertEntity(this)

    fun updateEntity() =
        DatabaseAPI.database.updateEntity(this)

    fun deleteEntity() =
        DatabaseAPI.database.deleteEntity(this)

}
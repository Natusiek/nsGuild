package pl.natusiek.mc.database.system

import pl.natusiek.mc.database.DatabaseAPI
import java.io.Serializable

abstract class DatabaseEntity(
        val id: String,
        val key: String,
        val collection: String
) : Serializable {

    fun insertEntity() =
        DatabaseAPI.database.insertEntity(this)

    fun updateEntity() =
        DatabaseAPI.database.updateEntity(this)

    fun deleteEntity() =
        DatabaseAPI.database.deleteEntity(this)

}
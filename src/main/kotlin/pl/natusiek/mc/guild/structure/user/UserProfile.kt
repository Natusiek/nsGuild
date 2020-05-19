package pl.natusiek.mc.guild.structure.user

import pl.natusiek.mc.database.system.DatabaseEntity
import java.io.Serializable
import java.util.*

data class UserProfile(
    val userId: UUID
) : DatabaseEntity(), Serializable {

    var guildId: UUID? = null

    override val id: String get() = TODO("Not yet implemented")
    override val key: String get() = TODO("Not yet implemented")
    override val collection: String get() = TODO("Not yet implemented")

}
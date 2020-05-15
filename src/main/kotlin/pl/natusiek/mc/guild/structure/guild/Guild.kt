package pl.natusiek.mc.guild.structure.guild

import pl.natusiek.mc.database.system.DatabaseEntity
import java.io.Serializable
import java.util.*
import kotlin.collections.HashSet

data class Guild(
    val guildId: UUID,
    val tag: String,
    val name: String,
    val leaderId: GuildMember
) : DatabaseEntity(), Serializable {

    val statistics = GuildStatistics(this)
    val region = GuildRegion(this)

    var members: MutableSet<GuildMember> = HashSet()
    var allies: MutableSet<UUID> = HashSet()

    override val id: String get() = this.guildId.toString()
    override val key: String get() = "guildId"
    override val collection: String get() = "guilds"


}
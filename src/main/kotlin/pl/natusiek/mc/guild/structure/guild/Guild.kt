package pl.natusiek.mc.guild.structure.guild

import pl.natusiek.mc.database.system.DatabaseEntity
import java.io.Serializable
import java.util.*
import kotlin.collections.HashSet

data class Guild(
    val guildId: UUID,
    val tag: String,
    val name: String,
    val leader: GuildMember
) : DatabaseEntity(), Serializable {

    val statistics = GuildStatistics(this)
    val region = GuildRegion(this)

    var members: MutableSet<GuildMember> = hashSetOf()
    var allies: MutableSet<UUID> = hashSetOf()

    var memberInvites: MutableSet<UUID> = hashSetOf()
    var alliesInvites: MutableSet<UUID> = hashSetOf()

    override val id: String get() = this.guildId.toString()
    override val key: String get() = "guildId"
    override val collection: String get() = "guilds"

    fun isLeaderByName(name: String) = this.leader.memberName == name

    fun isLeaderById(id: UUID) = this.leader.memberId == id

}
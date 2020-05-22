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
) : DatabaseEntity(
        guildId.toString(),
        "guildId",
        "guilds"
), Serializable {

    val statistics = GuildStatistics(this)
    val region = GuildRegion(this)

    var members: MutableSet<GuildMember> = hashSetOf()
    var allies: MutableSet<UUID> = hashSetOf()

    var memberInvites: MutableSet<UUID> = hashSetOf()
    var alliesInvites: MutableSet<UUID> = hashSetOf()


    fun isLeader(name: String) = this.leader.memberName == name
    fun isLeader(id: UUID) = this.leader.memberId == id

    fun getMember(name: String) = this.members.singleOrNull { it.memberName == name }
    fun getMember(id: UUID) = this.members.singleOrNull { it.memberId == id }

}
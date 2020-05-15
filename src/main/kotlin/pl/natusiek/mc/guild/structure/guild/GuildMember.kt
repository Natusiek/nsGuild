package pl.natusiek.mc.guild.structure.guild

import java.io.Serializable
import java.util.*

data class GuildMember(
    val memberId: UUID,
    val memberName: String
) : Serializable
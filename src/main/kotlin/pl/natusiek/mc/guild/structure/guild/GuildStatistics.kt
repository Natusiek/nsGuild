package pl.natusiek.mc.guild.structure.guild

import pl.natusiek.mc.common.extension.fixColor
import java.io.Serializable
import java.util.concurrent.TimeUnit

data class GuildStatistics(
    private val guild: Guild
) : Serializable {

    var kills: Int = 0
    var deaths: Int = 0
    var assists: Int = 0

    var points: Int = 500

    var pvp: Boolean = true

    var timeGuild: Long = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7)

}
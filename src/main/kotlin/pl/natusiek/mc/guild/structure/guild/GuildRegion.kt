package pl.natusiek.mc.guild.structure.guild

import org.bukkit.Location

data class GuildRegion(
        private val guild: Guild
) {

    val homeLocation: Location? = null
    val guildLocation: Location? = null
    val regionSize: Int = 40

}
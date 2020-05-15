package pl.natusiek.mc.guild.structure.guild

import org.bukkit.Location

data class GuildRegion(
        private val guild: Guild
) {

    val location: Location? = null
    val size: Int = 40

}
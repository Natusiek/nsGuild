package pl.natusiek.mc.guild.structure.guild

import org.bukkit.Location

data class GuildRegion(
        private val guild: Guild
) {

    var homeLocation: Location? = null
    var guildLocation: Location? = null
    var regionSize: Int = 40

}
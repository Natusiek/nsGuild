package pl.natusiek.mc.guild.event.default

import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import pl.natusiek.mc.guild.event.GuildEvent
import pl.natusiek.mc.guild.structure.guild.Guild

class CreateGuildEvent(
        val sender: Player,
        val guild: Guild,
        val location: Location
) : GuildEvent()